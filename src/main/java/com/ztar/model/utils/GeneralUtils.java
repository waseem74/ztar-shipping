package com.ztar.model.utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

public class GeneralUtils {

	private GeneralUtils() {

	}

	/**
	 * Return a formatted date with timezone
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(ZonedDateTime date, String pattern) {

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
		return date.format(dateFormatter);
	}

}
