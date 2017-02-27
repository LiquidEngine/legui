package org.liquidengine.legui.component;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joml.Vector2f;
import org.joml.Vector4f;
import org.liquidengine.legui.color.ColorConstants;
import org.liquidengine.legui.event.MouseClickEvent;
import org.liquidengine.legui.listener.MouseClickEventListener;

/**
 * An implementation of "toggle" button.
 * Behavior the same as checkbox but have not any text
 */
public class ToggleButton extends Button {
    protected ImageView togglededBackgroundImage;
    private   boolean   toggled;
    private   Vector4f  toggledBackgroundColor;

    /**
     * Creates a button with specified text and specified position and size.
     *
     * @param text   button text.
     * @param x      x position in parent
     * @param y      y position in parent
     * @param width  width of component
     * @param height height of component
     */
    public ToggleButton(String text, float x, float y, float width, float height) {
        super(text, x, y, width, height);
        initialize();
    }

    /**
     * Creates toggle button with default bg color and default toggled bg color.
     */
    public ToggleButton() {
        initialize();
    }

    /**
     * Creates toggle button with default bg color, toggled bg color and specified text.
     *
     * @param text button text.
     */
    public ToggleButton(String text) {
        super(text);
        initialize();
    }

    /**
     * Creates a button with specified position and size and default bg color and default toggled bg color.
     *
     * @param x      x position in parent
     * @param y      y position in parent
     * @param width  width of component
     * @param height height of component
     */
    public ToggleButton(float x, float y, float width, float height) {
        super(x, y, width, height);
        initialize();
    }

    /**
     * Creates a button with specified position and size and default bg color and default toggled bg color.
     *
     * @param position position in parent
     * @param size     size of component
     */
    public ToggleButton(Vector2f position, Vector2f size) {
        super(position, size);
        initialize();
    }

    /**
     * Initialize toggle button with default states
     */
    private void initialize() {
        setBackgroundColor(ColorConstants.red());
        toggledBackgroundColor = ColorConstants.green();
        MouseClickEventListener toggleButtonClickListener = new ToggleButtonMouseClickListener();
        getListenerMap().addListener(MouseClickEvent.class, toggleButtonClickListener);
    }

    /**
     * Returns true if toggle button is toggled.
     *
     * @return true if toggle button is toggled.
     */
    public boolean isToggled() {
        return toggled;
    }

    /**
     * Used to change toggled state.
     *
     * @param toggled new value
     */
    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }

    /**
     * Returns background color which will be used as background color if button toggled
     *
     * @return toggled button color
     */
    public Vector4f getToggledBackgroundColor() {
        return toggledBackgroundColor;
    }

    /**
     * Used to change background color which will be used as background color if button toggled.
     *
     * @param toggledBackgroundColor new value
     */
    public void setToggledBackgroundColor(Vector4f toggledBackgroundColor) {
        this.toggledBackgroundColor = toggledBackgroundColor;
    }

    /**
     * Returns toggled background image
     *
     * @return toggled background image
     */
    public ImageView getTogglededBackgroundImage() {
        return togglededBackgroundImage;
    }

    /**
     * Used to change toggled background image
     *
     * @param togglededBackgroundImage toggled background image
     */
    public void setTogglededBackgroundImage(ImageView togglededBackgroundImage) {
        this.togglededBackgroundImage = togglededBackgroundImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ToggleButton button = (ToggleButton) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(toggledBackgroundColor, button.toggledBackgroundColor)
                .append(toggled, button.toggled)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(toggledBackgroundColor)
                .append(toggled)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("toggledBackgroundColor", toggledBackgroundColor)
                .append("toggled", toggled)
                .toString();
    }

    private static class ToggleButtonMouseClickListener implements MouseClickEventListener {

        @Override
        public void process(MouseClickEvent event) {
            ToggleButton toggleButton = (ToggleButton) event.getComponent();
            if (event.getAction() == MouseClickEvent.MouseClickAction.CLICK) {
                toggleButton.setToggled(!toggleButton.isToggled());
            }
        }

        @Override
        public boolean equals(Object obj) {
            return true;
        }
    }
}
