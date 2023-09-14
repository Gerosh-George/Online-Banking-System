package com.ggtech.bankingapp.util;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.JsonNode;

public class Validator {

    public static Boolean nonNullValidator(JsonNode obj) {
		return obj!=null && !obj.isNull() && !obj.asText().isEmpty();
	}
	
	public static Boolean nonNullFieldsValidator(JsonNode obj, String [] nonNullFields) {
		if (obj!=null && !obj.isNull()) {
			for (String field : nonNullFields) {
				if(!nonNullValidator(obj.get(field))) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public static Boolean dateFormatValidator(JsonNode obj) throws Exception{
			String dateAsString = obj.asText();
			String pattern = "yyyy-MM-dd";
			if(!dateAsString.matches("^\\d{4}-\\d{2}-\\d{2}$"))
			return false;
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			sdf.setLenient(false);
			try {
				sdf.parse(dateAsString);
				return true;
			}
			catch (Exception e) {
				throw e;
			}
	}
}
