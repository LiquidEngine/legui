package org.liquidengine.legui.component;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joml.Vector2f;
import org.liquidengine.legui.color.ColorConstants;
import org.liquidengine.legui.component.optional.TextState;

/**
 * Class represent single line non-editable text component.
 */
public class Label extends Controller {

    /**
     * Used to hold text state of component.
     */
    private TextState textState;

    /**
     * Default constructor. Creates label with 'Label' text.
     */
    public Label() {
        this("Label");
    }

    /**
     * Creates label with specified size and on specified position.
     *
     * @param x      x position.
     * @param y      y position.
     * @param width  label width.
     * @param height label height.
     */
    public Label(float x, float y, float width, float height) {
        this("Label", x, y, width, height);
    }

    /**
     * Creates label with specified size and on specified position.
     *
     * @param position label position.
     * @param size     label size.
     */
    public Label(Vector2f position, Vector2f size) {
        this("Label", position, size);
    }

    /**
     * Creates label with specified text.
     *
     * @param text text to set.
     */
    public Label(String text) {
        initialize(text);
    }

    /**
     * Creates label with specified text, size and on specified position.
     *
     * @param text   text to set.
     * @param x      x position.
     * @param y      y position.
     * @param width  label width.
     * @param height label height.
     */
    public Label(String text, float x, float y, float width, float height) {
        super(x, y, width, height);
        initialize(text);
    }

    /**
     * Creates label with specified text, size and on specified position.
     *
     * @param text     text to set.
     * @param position label position.
     * @param size     label size.
     */
    public Label(String text, Vector2f position, Vector2f size) {
        super(position, size);
        initialize(text);
    }

    /**
     * Used to initialize label.
     *
     * @param text text to set.
     */
    private void initialize(String text) {
        this.textState = new TextState(text);
        setBackgroundColor(ColorConstants.transparent());
        setBorder(null);
    }

    /**
     * Used to retrieve text state.
     *
     * @return text state.
     */
    public TextState getTextState() {
        return textState;
    }

    /**
     * (non-Javadoc)
     *
     * @see Object#equals(Object)
     */
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

    /**
     * (non-Javadoc)
     *
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(textState)
                .toHashCode();
    }

    /**
     * (non-Javadoc)
     *
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("textState", textState)
                .toString();
    }

}
