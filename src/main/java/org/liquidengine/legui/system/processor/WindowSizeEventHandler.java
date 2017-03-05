package org.liquidengine.legui.system.processor;

import org.liquidengine.legui.component.Component;
import org.liquidengine.legui.component.Container;
import org.liquidengine.legui.component.Frame;
import org.liquidengine.legui.component.Layer;
import org.liquidengine.legui.event.WindowSizeEvent;
import org.liquidengine.legui.system.context.Context;
import org.liquidengine.legui.system.event.SystemWindowSizeEvent;

import java.util.Collections;
import java.util.List;

/**
 * Created by Aliaksandr_Shcherbin on 2/2/2017.
 */
public class WindowSizeEventHandler implements SystemEventHandler<SystemWindowSizeEvent> {
    @Override
    public void process(SystemWindowSizeEvent event, Frame frame, Context context) {
        List<Layer> layers = frame.getAllLayers();
        Collections.reverse(layers);
        for (Layer layer : layers) {
            if (layer.isEventReceivable()) {
                if (!layer.getContainer().isVisible() || !layer.getContainer().isEnabled()) continue;
                pushEvent(layer.getContainer(), event, context);
            }
        }
    }

    private void pushEvent(Component component, SystemWindowSizeEvent event, Context context) {
        if (!component.isVisible() || !component.isEnabled()) return;
        context.getEventProcessor().pushEvent(new WindowSizeEvent(component, context, event.width, event.height));
        if (component instanceof Container) {
            List<Component> childs = ((Container) component).getChilds();
            for (Component child : childs) {
                pushEvent(child, event, context);
            }
        }
    }
}
