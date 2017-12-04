package org.liquidengine.legui.binding;

import org.joml.Vector2f;
import org.liquidengine.legui.binding.model.BindingBuilder;
import org.liquidengine.legui.binding.model.ClassBinding;
import org.liquidengine.legui.component.Panel;
import org.liquidengine.legui.component.Widget;
import org.liquidengine.legui.marshal.json.JsonMarshaller;

/**
 * @author Aliaksandr_Shcherbin.
 */
public class Test {

    public static void main(String[] args) {
        BindingRegistry.getInstance().loadBindings("org/liquidengine/legui/binding/binding-list.xml");

        Vector2f c = new MyVec();
        c.set(10, 20);

        ClassBinding classBinding = BindingBuilder.createForClass(Vector2f.class, "vector", true, null).bind("x", "x_pos").bind("y", "y_pos").build();
        BindingRegistry.getInstance().setBinding(Vector2f.class, classBinding);
        String marshal = JsonMarshaller.marshal(c);
        System.out.println(marshal);
        MyVec v = JsonMarshaller.unmarshal(marshal, MyVec.class);
        System.out.println(c.equals(v));

        // -------------------

        Panel panelToMarshal = new Panel();
        panelToMarshal.add(new Panel(10,10,20,20));
        String panelJson = JsonMarshaller.marshal(panelToMarshal);
        System.out.println(panelJson);

        Panel widget = JsonMarshaller.unmarshal(panelJson, Panel.class);

        System.out.println(widget.equals(panelToMarshal));

    }

    public static class MyVec extends Vector2f {


    }


    private static class MyVec2 {

    }

}
