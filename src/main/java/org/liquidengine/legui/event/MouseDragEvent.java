package org.liquidengine.legui.event;

import org.joml.Vector2f;
import org.liquidengine.legui.component.Component;
import org.liquidengine.legui.system.context.Context;

/**
 * Created by Aliaksandr_Shcherbin on 2/9/2017.
 */
public class MouseDragEvent<T extends Component> extends AbstractEvent<T> {

    private final Vector2f delta;

    public MouseDragEvent(T component, Context context, Vector2f delta) {
        super(component, context);
        this.delta = delta;
    }

    public Vector2f getDelta() {
        return delta;
    }
}
