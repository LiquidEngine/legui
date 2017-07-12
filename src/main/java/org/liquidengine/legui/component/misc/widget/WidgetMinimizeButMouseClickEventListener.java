package org.liquidengine.legui.component.misc.widget;

import org.liquidengine.legui.component.Widget;
import org.liquidengine.legui.event.MouseClickEvent;
import org.liquidengine.legui.listener.MouseClickEventListener;

import static org.liquidengine.legui.event.MouseClickEvent.MouseClickAction.CLICK;

/**
 * @author Aliaksandr_Shcherbin.
 */
public class WidgetMinimizeButMouseClickEventListener implements MouseClickEventListener {
    private Widget widget;

    public WidgetMinimizeButMouseClickEventListener(Widget widget) {
        this.widget = widget;
    }

    public void process(MouseClickEvent event) {
        if (CLICK == event.getAction()) {
            boolean newValue = !widget.isMinimized();
            widget.getMinimizeButton().setBackgroundIcon(newValue ? widget.getMaximizeIcon() : widget.getMinimizeIcon());
            widget.setMinimized(newValue);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return (obj != null) && ((obj == this) || ((obj != this) && (obj.getClass() == this.getClass())));
    }
}
