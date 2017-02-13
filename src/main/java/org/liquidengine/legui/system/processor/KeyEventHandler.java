package org.liquidengine.legui.system.processor;

import org.liquidengine.legui.component.Component;
import org.liquidengine.legui.component.Frame;
import org.liquidengine.legui.event.KeyEvent;
import org.liquidengine.legui.input.Keyboard;
import org.liquidengine.legui.system.context.Context;
import org.liquidengine.legui.system.event.SystemKeyEvent;

import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

/**
 * Created by Aliaksandr_Shcherbin on 2/13/2017.
 */
public class KeyEventHandler implements SystemEventHandler<SystemKeyEvent> {
    @Override
    public void process(SystemKeyEvent event, Frame frame, Context context) {
        int keyCode = event.key;
        if (keyCode != -1) {
            Keyboard.Key key = Keyboard.Key.getByCode(keyCode);
            if (key != null) {
                key.setPressed(event.action != GLFW_RELEASE);
            }
        }

        Component focusedGui = context.getFocusedGui();
        if (focusedGui == null) return;

        context.getEventProcessor().pushEvent(new KeyEvent(focusedGui, context, event.action, keyCode, event.mods, event.scancode));
    }
}
