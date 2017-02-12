package org.liquidengine.legui.component;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joml.Vector2f;
import org.liquidengine.legui.color.ColorConstants;
import org.liquidengine.legui.component.optional.TextState;

/**
 * Created by Aliaksandr_Shcherbin on 2/6/2017.
 */
public class Label extends Controller {

    private TextState textState;

    public Label() {
        this("Label");
    }

    public Label(float x, float y, float width, float height) {
        this("Label", x, y, width, height);
    }

    public Label(Vector2f position, Vector2f size) {
        this("Label", position, size);
    }

    public Label(String text) {
        initialize(text);
    }

    public Label(String text, float x, float y, float width, float height) {
        super(x, y, width, height);
        initialize(text);
    }

    public Label(String text, Vector2f position, Vector2f size) {
        super(position, size);
        initialize(text);
    }

    private void initialize(String text) {
        this.textState = new TextState(text);
        setBackgroundColor(ColorConstants.transparent());
        setBorder(null);
    }

    public TextState getTextState() {
        return textState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Label label = (Label) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(textState, label.textState)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(textState)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("textState", textState)
                .toString();
    }

}
