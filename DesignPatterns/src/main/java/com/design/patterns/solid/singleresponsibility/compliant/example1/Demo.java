package com.design.patterns.solid.singleresponsibility.compliant.example1;

public class Demo {

	public static void main(String[] args) {
		Book book = new Book("ABC", 10, "DEF");

		System.out.println(book.getCurrentPage());
		book.turnPage();
		System.out.println(book.getCurrentPage());

		Printer htmlPrinter = new HTMLPrinter();
		htmlPrinter.print(book.getCurrentPage());

		Printer notePadPrinter = new NotePadPrinter();

		notePadPrinter.print(book.getCurrentPage());
	}

}

class Book {

	private String name;
	private int pages;
	private String author;
	private int currPage;

	public Book(String name, int pages, String author) {
		super();
		this.name = name;
		this.pages = pages;
		this.author = author;
		this.currPage = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void turnPage() {
		if (currPage < pages) {
			System.out.println("Turning Page to page No: " + (++currPage));
		} else {
			System.out.println("You are at the last page!!!");
		}
	}

	public String getCurrentPage() {
		return "Here is page " + currPage;
	}

}

interface Printer {
	public void print(String page);
}

class HTMLPrinter implements Printer {

	@Override
	public void print(String page) {
		System.out.println("Printing in HTML: " + page);
	}
}

class NotePadPrinter implements Printer {

	@Override
	public void print(String page) {
		System.out.println("Printing in NotePad: " + page);

	}

}