package de.niklasmehner;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

@Startup
@Singleton
public class SomeSingleton {

	@Inject
	private SomeBean someBean;

	@PostConstruct
	public void test() {
		try {
			someBean.hello(null);
		} catch (ConstraintViolationException e) {
			System.out.println(e.getConstraintViolations().iterator().next().getMessage());
		}
	}
}
