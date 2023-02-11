package com.design.patterns.factorymethod.example2;

/**
 * All products families have the same varieties (MacOS/Windows).
 *
 * This is a variant of a checkbox.
 */
public class MacOSCheckbox implements CheckBox {

	@Override
	public void paint() {
		System.out.println("You have created MacOSCheckbox.");
	}

}
