package org.liquidengine.legui.theme.dark.def;

import org.liquidengine.legui.border.SimpleLineBorder;
import org.liquidengine.legui.color.ColorConstants;
import org.liquidengine.legui.component.Component;
import org.liquidengine.legui.theme.AbstractTheme;

/**
 * Dark Component Theme for all components. Used to make component dark.
 *
 * @param <T> {@link Component} subclasses.
 */
public class DarkComponentTheme<T extends Component> extends AbstractTheme<T> {
    /**
     * Used to apply theme only for component and not apply for child components.
     *
     * @param component component to apply theme.
     */
    @Override
    public void apply(T component) {
        component.setBorder(new SimpleLineBorder(ColorConstants.lightGray(), 1.2f));
        component.setCornerRadius(2);
        component.setBackgroundColor(ColorConstants.darkGray());
    }

    /**
     * Used to apply theme only for component and not apply for child components.
     * Applies changes only for current class part and for parent part.
     *
     * @param component component to apply theme.
     */
    @Override
    public void applyWithParent(T component) {
        super.applyWithParent(component);
        super.apply(component);
        apply(component);
    }

    /**
     * Used to apply theme for component and for all children of this component.
     * Applies changes only for current class part and for parent part.
     *
     * @param component component to apply theme.
     */
    @Override
    public void applyAllWithParent(T component) {
        super.applyAllWithParent(component);
        super.applyAll(component);
        applyAll(component);
    }
}