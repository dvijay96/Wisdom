package com.design.patterns.factorymethod.example2;

/**
 * Abstract factory knows about all (abstract) product types.
 */
public interface GUIFactory {
	Button createButton();

	CheckBox createCheckbox();
}
