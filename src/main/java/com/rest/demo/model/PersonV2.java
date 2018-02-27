package com.rest.demo.model;

/**
 * @author r3demo
 * Response Model used by the PersonVersioningController (V2)
 *
 */

public class PersonV2 {

	private Name name;

	public PersonV2() {
		super();
	}

	public PersonV2(Name name) {
		super();
		this.name = name;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}	
}
