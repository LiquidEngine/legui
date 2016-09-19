package org.liquidengine.legui.processor.system;

import org.liquidengine.legui.component.Component;
import org.liquidengine.legui.context.LeguiContext;
import org.liquidengine.legui.event.system.DropEvent;

/**
 * Created by Shcherbin Alexander on 7/13/2016.
 */
public class GuiDropEventCallback extends LeguiSystemEventProcessor<DropEvent> {
    public GuiDropEventCallback(LeguiContext context) {
        super(context);
    }

    @Override
    public void processEvent(DropEvent event, Component mainGui, Component target) {
        if (target != null) {
            target.getProcessors().getDropEventProcessor().process(target, event, context);
        } else {
            mainGui.getProcessors().getDropEventProcessor().process(mainGui, event, context);
        }
    }
}
