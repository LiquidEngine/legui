package com.liquidengine.gui.component;

import com.liquidengine.gui.component.additional.Border;
import org.joml.Vector2f;
import org.joml.Vector4f;

/**
 * Component is an object that have graphical representation in gui system
 * <p>
 * Created by Shcherbin Alexander on 9/14/2016.
 */
public abstract class Component {
    protected Vector2f position;
    protected Vector2f size;

    protected Vector4f backgroundColor;

    protected Border border;

    protected boolean enabled;
    protected boolean visible;
    protected boolean focused;

    protected ComponentContainer parent;

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public Vector2f getSize() {
        return size;
    }

    public void setSize(Vector2f size) {
        this.size = size;
    }

    public Vector4f getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Vector4f backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Border getBorder() {
        return border;
    }

    public void setBorder(Border border) {
        this.border = border;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isFocused() {
        return focused;
    }

    public void setFocused(boolean focused) {
        this.focused = focused;
    }
}
