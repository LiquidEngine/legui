package org.liquidengine.legui.theme;

import org.liquidengine.legui.component.Component;


/**
 * This interface defines method which can be used to change theme only for provided component.
 * Children and parent components should not be changed by this method.
 */
public abstract class AbstractTheme<T extends Component> {

    /**
     * Used to apply theme only for component and not apply for child components.
     *
     * @param component component to apply theme.
     */
    public abstract void apply(T component);

    /**
     * Used to apply theme for component and for all children of this component.
     * Should be reimplemented for components that contains other child components.
     *
     * @param component component to apply theme.
     */
    public void applyAll(T component) {
        apply(component);
    }
}
