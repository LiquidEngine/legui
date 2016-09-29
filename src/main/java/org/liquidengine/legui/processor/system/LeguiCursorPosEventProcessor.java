package org.liquidengine.legui.processor.system;

import org.joml.Vector2f;
import org.liquidengine.legui.component.Component;
import org.liquidengine.legui.component.ComponentContainer;
import org.liquidengine.legui.component.ContainerHolder;
import org.liquidengine.legui.context.LeguiContext;
import org.liquidengine.legui.event.component.MouseDragEvent;
import org.liquidengine.legui.event.system.CursorPosEvent;

import java.util.List;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;

/**
 * Event processor for cursor events. Updates GUI element depending on cursor position. Calls CursorEventListeners of GUI element
 * Created by Shcherbin Alexander on 6/16/2016.
 */
public class LeguiCursorPosEventProcessor extends LeguiSystemEventProcessor<CursorPosEvent> {

    public LeguiCursorPosEventProcessor(LeguiContext context) {
        super(context);
    }

    @Override
    public void processEvent(CursorPosEvent event, Component mainGui) {
        Component target = context.getMouseTargetGui();
        Vector2f cursorPosition = context.getCursorPosition();
        context.getCursorPositionPrev().set(cursorPosition);
        cursorPosition.set((float) event.xpos, (float) event.ypos);
        process(event, mainGui, target);
    }

    private void process(CursorPosEvent event, Component gui, Component target) {
        updateComponentStatesAndCallListeners(event, gui, target);
        gui.getProcessors().getCursorPosEventProcessor().process(gui, event, context);
        if (gui instanceof ContainerHolder) {
            processEventOnContainer(event, gui, target);
        }
    }


    private void processEventOnContainer(CursorPosEvent event, Component gui, Component target) {
        ComponentContainer container = ((ContainerHolder) gui).getContainer();
        List<Component> all = container.getComponents();
        for (Component element : all) {
            process(event, element, target);
        }
    }

    /**
     * Updates standard context of gui element
     *
     * @param event
     * @param gui
     */
    private void updateComponentStatesAndCallListeners(CursorPosEvent event, Component gui, Component target) {
        if (target != null && context.getMouseButtonStates()[GLFW_MOUSE_BUTTON_LEFT]) {
            Vector2f cursorPosition = new Vector2f(context.getCursorPosition());
            Vector2f cursorPositionPrev = new Vector2f(context.getCursorPositionPrev());
            MouseDragEvent mouseDragEvent = new MouseDragEvent(cursorPosition, cursorPositionPrev, gui);
            target.getComponentListenerHolder().getMouseDragEventListeners().forEach(listener -> listener.onMouseDrag(mouseDragEvent));
        }
//        List<CursorEnterListener> listeners = gui.getListeners().getCursorEnterListeners();
//        Vector2f position = Util.calculatePosition(gui);
//        Vector2f cursorPosition = context.getCursorPosition();
//        boolean intersects = gui.getIntersector().intersects(gui, cursorPosition);
//        Vector2f mousePosition = position.sub(cursorPosition).negate();
//        if (gui == target && !intersects) {
//            listeners.forEach(listener -> listener.onCursorOut(mousePosition));
//        } else if (gui != target && intersects) {
//            listeners.forEach(listener -> listener.onCursorIn(mousePosition));
//        }
    }
}
