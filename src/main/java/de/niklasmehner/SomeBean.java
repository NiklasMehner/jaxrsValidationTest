package de.niklasmehner;

import javax.enterprise.context.Dependent;
import javax.validation.constraints.NotNull;

@Dependent
public class SomeBean {

	public void hello(@NotNull String name) {
		
	}

}
