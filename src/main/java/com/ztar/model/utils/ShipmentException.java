package com.ztar.model.utils;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.ztar.config.Translator;

public class ShipmentException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private ApplicationContext context;


	@Autowired
	public void context(ApplicationContext context) { this.context = context; }
	
	private final String messageKey;
	private final Locale locale;
	private final Object[] args;

	public ShipmentException(String messageKey) {
		this(messageKey, Locale.getDefault());
	}

	public ShipmentException(String messageKey, Object[] args) {
		this(messageKey, args, Locale.getDefault());
	}

	public ShipmentException(String messageKey, Locale locale) {
		this(messageKey, null, locale);
	}

	public ShipmentException(String messageKey, Object[] args, Locale locale) {
		this.messageKey = messageKey;
		this.args = args;
		this.locale = locale;
	}

	@Override
	public String getMessage() {
		return Translator.toLocale(messageKey, args);
	}

}
