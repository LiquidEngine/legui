package org.liquidengine.legui.component.misc.listener.slider;

import org.joml.Vector2f;
import org.liquidengine.legui.component.Slider;
import org.liquidengine.legui.component.optional.Orientation;
import org.liquidengine.legui.input.Mouse;

import java.math.BigDecimal;

class SliderHelper {

	/**
	 * Calculates the sliders new value for the given mouse position.
	 * @param slider the slider to calculate the value for
	 * @param mousePosition the position of the mouse cursor
	 * @return the sliders new value
	 */
	static BigDecimal determineSliderValue(Slider slider, Vector2f mousePosition) {
		Vector2f pos = slider.getAbsolutePosition();
		float sliderSize = slider.getSliderSize();
		float minValue = slider.getMinValue();
		float maxValue = slider.getMaxValue();
		float difference = maxValue - minValue;
		float percentage;
		if (Orientation.VERTICAL.equals(slider.getOrientation())) {
			percentage = (mousePosition.y - pos.y - sliderSize / 2f) / (slider.getSize().y - sliderSize);
		} else {
			percentage = (mousePosition.x - pos.x - sliderSize / 2f) / (slider.getSize().x - sliderSize);
		}
		BigDecimal value = new BigDecimal(difference * percentage + minValue);
		// check for min/max values
		if (value.floatValue() > slider.getMaxValue()) {
			value = new BigDecimal(slider.getMaxValue());
		}
		if (value.floatValue() < slider.getMinValue()) {
			value = new BigDecimal(slider.getMinValue());
		}
		return value;
	}

}