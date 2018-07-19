package com.airtel.merchant.scheduler.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.airtel.merchant.scheduler.cons.UpiConstants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * The Class MRDateSerializer will serialize date field.
 */
public class MRDateSerializer extends JsonSerializer<Date> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fasterxml.jackson.databind.JsonSerializer#serialize(java.lang.Object,
	 * com.fasterxml.jackson.core.JsonGenerator,
	 * com.fasterxml.jackson.databind.SerializerProvider)
	 */
	@Override
	public void serialize(Date date, JsonGenerator gen, SerializerProvider provider) throws IOException {
		DateFormat dateFormat = new SimpleDateFormat(UpiConstants.YYYY_MM_DD_HH_MM_SS_TIMEZONE);
		dateFormat.setTimeZone(TimeZone.getTimeZone(UpiConstants.UTC));
		String formattedDate = dateFormat.format(date);
		gen.writeString(formattedDate);

	}

}
