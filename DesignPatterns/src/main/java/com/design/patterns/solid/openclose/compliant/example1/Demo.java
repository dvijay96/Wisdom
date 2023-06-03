package com.design.patterns.solid.openclose.compliant.example1;

import java.util.concurrent.ThreadLocalRandom;

public class Demo {

	public static void main(String[] args) {

		Marker marker = new Marker("Cello", "Red", 2021, 25);

		Invoice invoice = new Invoice(marker, 3);

		System.out.println(invoice.calculateTotal());

		InvoiceDao dao = new DBInvoiceDao();
		dao.save(invoice);

		dao = new FileInvoiceDao();
		dao.save(invoice);
	}

}

interface InvoiceDao {
	void save(Invoice invoice);
}

class DBInvoiceDao implements InvoiceDao {

	public DBInvoiceDao() {
		super();
	}

	@Override
	public void save(Invoice invoice) {
		System.out.println("Saving invoice to DB !!! \n id:- " + invoice.getId());
	}

}

class FileInvoiceDao implements InvoiceDao {

	public FileInvoiceDao() {
		super();
	}

	// New Requirement

	@Override
	public void save(Invoice invoice) {
		System.out.println("Saving invoice to File !!! \n id:- " + invoice.getId());
	}

	// We should extend the current functionalities and then modify our new
	// requirements.
}

class Invoice {
	private int id;
	private Marker marker;
	private int quantity;

	public Invoice(Marker marker, int quantity) {
		super();
		this.marker = marker;
		this.quantity = quantity;
		this.id = ThreadLocalRandom.current().nextInt(500);
	}

	public int getId() {
		return id;
	}

	public int calculateTotal() {
		return marker.price * this.quantity; // If invoice logic change, change this method
	}

	// Only one reason to change
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