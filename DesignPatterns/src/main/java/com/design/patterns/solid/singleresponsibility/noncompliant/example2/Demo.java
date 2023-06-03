package com.design.patterns.solid.singleresponsibility.noncompliant.example2;

public class Demo {

	public static void main(String[] args) {
		Marker marker = new Marker("Cello", "Red", 2021, 25);

		Invoice invoice = new Invoice(marker, 3);

		System.out.println(invoice.calculateTotal());

		invoice.printInvoice();
		invoice.saveToDb();

	}

}

class Invoice {
	private Marker marker;
	private int quantity;

	public Invoice(Marker marker, int quantity) {
		super();
		this.marker = marker;
		this.quantity = quantity;
	}

	public int calculateTotal() {
		return marker.price * this.quantity; // If invoice logic change, change this method
	}

	public void printInvoice() {
		System.out.println("Printing invoice !!!"); // If printing logic changes ,change this method
	}

	public void saveToDb() {
		System.out.println("Saving invoice to DB !!!"); // If saving logic changes ,change this method
	}

	// Total 3 reasons to change this class. Not a Single resp.
}

class Marker {
	String name;
	String color;
	int manfacturingYear;
	int price;

	public Marker(String name, String color, int manfacturingYear, int price) {
		super();
		this.name = name;
		this.color = color;
		this.manfacturingYear = manfacturingYear;
		this.price = price;
	}

}
