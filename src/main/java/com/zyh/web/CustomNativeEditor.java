package com.zyh.web;

import java.beans.PropertyEditorSupport;
import java.text.NumberFormat;

import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

public class CustomNativeEditor extends PropertyEditorSupport {

	@SuppressWarnings("rawtypes")
	private final Class numberClass;

	private final NumberFormat numberFormat;

	private final boolean allowEmpty;

	@SuppressWarnings("rawtypes")
	public CustomNativeEditor(Class numberClass, boolean allowEmpty)
			throws IllegalArgumentException {
		this(numberClass, null, allowEmpty);
	}

	@SuppressWarnings("rawtypes")
	public CustomNativeEditor(Class numberClass, NumberFormat numberFormat, boolean allowEmpty)
			throws IllegalArgumentException {
		if (numberClass == null || !Number.class.isAssignableFrom(numberClass)) {
			throw new IllegalArgumentException("Property class must be a subclass of Number");
		}
		this.numberClass = numberClass;
		this.numberFormat = numberFormat;
		this.allowEmpty = allowEmpty;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void setAsText(String text) throws IllegalArgumentException {
		if (this.allowEmpty && !StringUtils.hasText(text)) {
			setValue(0);// 这里就是整型为对空值的处理；
		} else if (this.numberFormat != null) {
			setValue(NumberUtils.parseNumber(text, this.numberClass, this.numberFormat));
		} else {
			setValue(NumberUtils.parseNumber(text, this.numberClass));
		}
	}

	/**
	 * Coerce a Number value into the required target class, if necessary.
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void setValue(Object value) {
		if (value instanceof Number) {
			super.setValue(NumberUtils.convertNumberToTargetClass((Number) value, this.numberClass));
		} else {
			super.setValue(value);
		}
	}

	/**
	 * Format the Number as String, using the specified NumberFormat.
	 */
	@Override
	public String getAsText() {
		Object value = getValue();
		if (value == null) {
			return "";
		}
		if (this.numberFormat != null) {
			// Use NumberFormat for rendering value.
			return this.numberFormat.format(value);
		} else {
			// Use toString method for rendering value.
			return value.toString();
		}
	}

}