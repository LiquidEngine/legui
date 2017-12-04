package org.liquidengine.legui.binding.model;

/**
 * Default binding implementation.
 *
 * @param <T> type of class binding.
 *
 * @author ShchAlexander.
 */
public final class ClassBinding<T> extends AbstractClassBinding<T> {

    /**
     * Constructs class binding.
     *
     * @param bindingForType type for which binding is created.
     * @param byDefault should this binding used as default or not.
     */
    public ClassBinding(Class<? extends T> bindingForType, boolean byDefault) {
        super(bindingForType, byDefault);
    }
}
