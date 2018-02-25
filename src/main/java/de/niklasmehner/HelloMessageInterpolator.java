package de.niklasmehner;

import java.util.Collections;
import java.util.Locale;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.MessageInterpolator;
import javax.ws.rs.core.HttpHeaders;

@Dependent
public class HelloMessageInterpolator implements MessageInterpolator {
	
	@javax.ws.rs.core.Context
	private HttpHeaders headers;

	@Inject
	private Instance<HttpServletRequest> request;

	@Override
	public String interpolate(String messageTemplate, Context context) {		
		if (headers.getAcceptableLanguages() != null) {
			return "Interpolating: " + messageTemplate + " with locale from headers " + headers.getAcceptableLanguages();			
		} else if (isAvailable()) {
			return "Interpolating: " + messageTemplate + " with locale from servlet request " + Collections.list(request.get().getLocales());						
		} else {
			return "Interpolating: " + messageTemplate + " with default locale " + Locale.getDefault();						
		}		
	}

	private boolean isAvailable() {
		if (request.isUnsatisfied() || !request.isResolvable()) {
			return false;
		}
		try {
			request.get().getLocale();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String interpolate(String messageTemplate, Context context, Locale locale) {
		return "Interpolating: " + messageTemplate + " with locale " + locale;
	}

}
