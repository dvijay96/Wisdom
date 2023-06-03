package com.design.patterns.solid.dependencyip.compliant.example1;

public class Demo {

	public static void main(String[] args) {

		Laptop laptop = new Laptop(new WiredMouse(), new BluetoothKeyBoard());

		laptop = new Laptop(new BluetoothMouse(), new WiredKeyBoard());
	}

}

class Laptop {
	private final Mouse mouse;
	private final KeyBoard keyboard;

	public Laptop(Mouse mouse, KeyBoard keyboard) {
		super();
		this.mouse = mouse;
		this.keyboard = keyboard;
	}

	// assigning interface classes to dependencies ( loose coupling )
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

class BluetoothKeyBoard implements KeyBoard {

}