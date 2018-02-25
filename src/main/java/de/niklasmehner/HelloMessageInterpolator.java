package de.niklasmehner;

import java.util.Collections;
import java.util.Locale;

import javax.enterprise.context.Dependent;
import javax.servlet.http.HttpServletRequest;
import javax.validation.MessageInterpolator;
import javax.ws.rs.core.HttpHeaders;

@Dependent
public class HelloMessageInterpolator implements MessageInterpolator {
	
	@javax.ws.rs.core.Context
	private HttpHeaders headers;

	@javax.ws.rs.core.Context
	private HttpServletRequest request;

	@Override
	public String interpolate(String messageTemplate, Context context) {		
		if (headers.getAcceptableLanguages() != null) {
			return "Interpolating: " + messageTemplate + " with locale from headers " + headers.getAcceptableLanguages();			
		} else if (request.getLocales() != null) {
			return "Interpolating: " + messageTemplate + " with locale from servlet request " + Collections.list(request.getLocales());						
		} else {
			return "Interpolating: " + messageTemplate + " with default locale " + Locale.getDefault();						
		}		
	}

	@Override
	public String interpolate(String messageTemplate, Context context, Locale locale) {
		return "Interpolating: " + messageTemplate + " with locale " + locale;
	}

}
