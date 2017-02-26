package org.liquidengine.legui.component;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joml.Vector2f;
import org.joml.Vector4f;
import org.liquidengine.legui.color.ColorConstants;
import org.liquidengine.legui.component.optional.Orientation;
import org.liquidengine.legui.event.MouseClickEvent;
import org.liquidengine.legui.event.MouseDragEvent;
import org.liquidengine.legui.event.ScrollEvent;
import org.liquidengine.legui.input.Mouse;
import org.liquidengine.legui.intersection.Intersector;
import org.liquidengine.legui.intersection.RectangleIntersector;
import org.liquidengine.legui.listener.EventListener;
import org.liquidengine.legui.listener.MouseClickEventListener;
import org.liquidengine.legui.listener.MouseDragEventListener;
import org.liquidengine.legui.listener.ScrollEventListener;
import org.liquidengine.legui.system.context.Context;

/**
 * Implementation of slider controller.
 */
public class Slider extends Controller {

    public static final float MAX_VALUE = 100f;
    public static final float MIN_VALUE = 0f;

    private float value;

    private Orientation orientation = Orientation.HORIZONTAL;

    private Vector4f sliderActiveColor = new Vector4f(0, 0, 1, 1);
    private Vector4f sliderColor       = new Vector4f(0.7f, 0.7f, 0.7f, 0.4f);

    private float sliderSize = 10f;

    public Slider(float value) {
        initialize(value);
    }

    public Slider(float x, float y, float width, float height, float value) {
        super(x, y, width, height);
        initialize(value);
    }

    public Slider() {
        this(0f);
    }

    public Slider(float x, float y, float width, float height) {
        this(x, y, width, height, 0f);
    }

    private void initialize(float value) {
        this.value = value;
        setBackgroundColor(ColorConstants.transparent());
        setBorder(null);
        getListenerMap().addListener(ScrollEvent.class, new SliderScrollEventListener(this));
        getListenerMap().addListener(MouseClickEvent.class, new SliderMouseClickEventListener(this));
        getListenerMap().addListener(MouseDragEvent.class, new SliderMouseDragEventListener(this));
    }

    @Override
    public void setIntersector(Intersector intersector) {
        if (intersector == null || !(intersector instanceof RectangleIntersector)) return;
        super.setIntersector(intersector);
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value < MIN_VALUE ? MIN_VALUE : value > MAX_VALUE ? MAX_VALUE : value;
    }

    public Vector4f getSliderColor() {
        return sliderColor;
    }

    public void setSliderColor(Vector4f sliderColor) {
        this.sliderColor = sliderColor;
    }

    public float getSliderSize() {
        return sliderSize;
    }

    public void setSliderSize(float sliderSize) {
        this.sliderSize = sliderSize;
    }


    public Vector4f getSliderActiveColor() {
        return sliderActiveColor;
    }

    public void setSliderActiveColor(Vector4f sliderActiveColor) {
        this.sliderActiveColor = sliderActiveColor;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Slider slider = (Slider) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(value, slider.value)
                .append(sliderSize, slider.sliderSize)
                .append(orientation, slider.orientation)
                .append(sliderActiveColor, slider.sliderActiveColor)
                .append(sliderColor, slider.sliderColor)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(value)
                .append(orientation)
                .append(sliderActiveColor)
                .append(sliderColor)
                .append(sliderSize)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("value", value)
                .append("orientation", orientation)
                .append("sliderActiveColor", sliderActiveColor)
                .append("sliderColor", sliderColor)
                .append("sliderSize", sliderSize)
                .toString();
    }

    public interface SliderChangeValueEventListener extends EventListener<SliderChangeValueEvent> {
        void process(SliderChangeValueEvent event);
    }

    public static class SliderScrollEventListener implements ScrollEventListener {

        private final Slider slider;

        public SliderScrollEventListener(Slider slider) {
            this.slider = slider;
        }

        @Override
        public void process(ScrollEvent event) {
            float curValue = slider.getValue();
            float value    = (float) (curValue + event.getYoffset());

            if (value > MAX_VALUE) value = MAX_VALUE;
            if (value < MIN_VALUE) value = MIN_VALUE;

            event.getContext().getEventProcessor().pushEvent(new SliderChangeValueEvent(slider, event.getContext(), slider.getValue(), value));
            slider.setValue(value);
        }
    }

    public static class SliderMouseClickEventListener implements MouseClickEventListener {
        private final Slider slider;

        public SliderMouseClickEventListener(Slider slider) {
            this.slider = slider;
        }

        @Override
        public void process(MouseClickEvent event) {
            if (event.getButton().equals(Mouse.MouseButton.MOUSE_BUTTON_LEFT) && event.getAction() == MouseClickEvent.MouseClickAction.PRESS) {
                Vector2f pos = slider.getScreenPosition();

                Vector2f cursorPosition = Mouse.getCursorPosition();
                float    value;
                if (Orientation.VERTICAL.equals(slider.getOrientation())) {
                    value = 100f * (pos.y + slider.getSize().y - cursorPosition.y - slider.sliderSize / 2f) / (slider.getSize().y - slider.sliderSize);
                } else {
                    value = 100f * (cursorPosition.x - pos.x - slider.sliderSize / 2f) / (slider.getSize().x - slider.sliderSize);
                }
                if (value > MAX_VALUE) value = MAX_VALUE;
                if (value < MIN_VALUE) value = MIN_VALUE;
                event.getContext().getEventProcessor().pushEvent(new SliderChangeValueEvent(slider, event.getContext(), slider.getValue(), value));
                slider.setValue(value);
            }
        }
    }

    public static class SliderMouseDragEventListener implements MouseDragEventListener {
        private final Slider slider;

        public SliderMouseDragEventListener(Slider slider) {
            this.slider = slider;
        }

        @Override
        public void process(MouseDragEvent event) {
            if (!Mouse.MouseButton.MOUSE_BUTTON_LEFT.isPressed()) return;

            Vector2f pos = slider.getScreenPosition();

            Vector2f cursorPosition = Mouse.getCursorPosition();
            float    value;
            if (Orientation.VERTICAL.equals(slider.getOrientation())) {
                value = 100f * ((pos.y + slider.getSize().y) - cursorPosition.y - slider.sliderSize / 2f) / (slider.getSize().y - slider.sliderSize);
            } else {
                value = 100f * (cursorPosition.x - pos.x - slider.sliderSize / 2f) / (slider.getSize().x - slider.sliderSize);
            }

            if (value > MAX_VALUE) value = MAX_VALUE;
            if (value < MIN_VALUE) value = MIN_VALUE;

            event.getContext().getEventProcessor().pushEvent(new SliderChangeValueEvent(slider, event.getContext(), slider.getValue(), value));
            slider.setValue(value);
        }
    }

    public static class SliderChangeValueEvent extends org.liquidengine.legui.event.AbstractEvent {

        private final float oldValue;
        private final float newValue;

        public SliderChangeValueEvent(Component component, Context context, float oldValue, float newValue) {
            super(component, context);
            this.oldValue = oldValue;
            this.newValue = newValue;
        }

        public float getNewValue() {
            return newValue;
        }

        public float getOldValue() {
            return oldValue;
        }
    }

}
