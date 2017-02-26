package org.liquidengine.legui.marshal.json.gsonImpl.component;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.liquidengine.legui.component.SelectBox;
import org.liquidengine.legui.marshal.json.gsonImpl.GsonMarshalContext;
import org.liquidengine.legui.marshal.json.gsonImpl.GsonUtil;

import java.util.List;

import static org.liquidengine.legui.marshal.JsonConstants.*;
import static org.liquidengine.legui.marshal.json.gsonImpl.GsonUtil.isNotNull;

/**
 * Created by ShchAlexander on 27.02.2017.
 */
public class GsonSelectBoxMarshaller<T extends SelectBox> extends GsonControllerMarshaller<T> {
    /**
     * Reads data from object and puts it to json object
     *
     * @param object  object to read
     * @param json    json object to fill
     * @param context marshal context
     */
    @Override
    protected void jsonMarshal(T object, JsonObject json, GsonMarshalContext context) {
        super.jsonMarshal(object, json, context);

        GsonUtil.fill(json)
                .add(SELECTED_ELEMENT, object.getSelection())
                .add(ELEMENT_HEIGHT, object.getElementHeight())
                .add(BUTTON_WIDTH, object.getButtonWidth())
                .add(VISIBLE_COUNT, object.getVisibleCount())
        ;
        List<String> elements     = object.getElements();
        JsonArray    jsonElements = new JsonArray();
        for (String element : elements) {
            jsonElements.add(element);
        }
        json.add(ELEMENTS, jsonElements);
    }

    /**
     * Reads data from json object and puts it to object
     *
     * @param json    json object to read
     * @param object  object to fill
     * @param context marshal context
     */
    @Override
    protected void unmarshal(JsonObject json, T object, GsonMarshalContext context) {
        super.unmarshal(json, object, context);

        JsonElement elements        = json.get(ELEMENTS);
        JsonElement selectedElement = json.get(SELECTED_ELEMENT);
        JsonElement elementHeight   = json.get(ELEMENT_HEIGHT);
        JsonElement buttonWidth     = json.get(BUTTON_WIDTH);
        JsonElement visibleCount    = json.get(VISIBLE_COUNT);

        if (isNotNull(elements) && elements.isJsonArray()) {
            JsonArray el = elements.getAsJsonArray();
            for (JsonElement jsonElement : el) {
                object.addElement(jsonElement.getAsString());
            }
        }

        if (isNotNull(selectedElement)) object.setSelected(selectedElement.getAsString(), true);
        if (isNotNull(elementHeight)) object.setElementHeight(elementHeight.getAsFloat());
        if (isNotNull(buttonWidth)) object.setButtonWidth(buttonWidth.getAsFloat());
        if (isNotNull(visibleCount)) object.setVisibleCount(visibleCount.getAsInt());
    }
}
