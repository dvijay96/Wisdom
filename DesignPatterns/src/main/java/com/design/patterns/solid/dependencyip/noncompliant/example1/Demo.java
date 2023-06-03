package com.design.patterns.solid.dependencyip.noncompliant.example1;

public class Demo {

	public static void main(String[] args) {

		Laptop laptop = new Laptop(new WiredMouse(), new WiredKeyBoard());
	}

}

class Laptop {
	private final WiredMouse mouse;
	private final WiredKeyBoard keyboard;

	public Laptop(WiredMouse mouse, WiredKeyBoard keyboard) {
		super();
		this.mouse = mouse;
		this.keyboard = keyboard;
	}

	// assigning concrete classes to dependencies rather than interface ( tight
	// coupling )
	// In future can't change to bluetooth mouse or keyboard ( strictly non
	// compatible ).
}

interface Mouse {

}

interface KeyBoard {

}

class WiredMouse implements Mouse {

}

class BluetoothMouse implements Mouse {

}

class WiredKeyBoard implements KeyBoard {

}

class BluetoothBoard implements KeyBoard {

}