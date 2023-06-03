package com.design.patterns.solid.singleresponsibility.noncompliant.example1;

public class Demo {

//	A Module should have one and only one responsibility.
//	A Module should have one and only one reason to change.

//	It makes software easier to implement and prevent unexpected side effects of future changes.

	public static void main(String[] args) {
		Book book = new Book("ABC", 10, "DEF");

		System.out.println(book.getCurrentPage());
		book.turnPage();
		System.out.println(book.getCurrentPage());

		// Book having two diff responsibilities of printing
		System.out.println(book.printPageInHtml());
		System.out.println(book.printPageInNotepad());
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

	public String printPageInHtml() {
		return "Printing page in HTML " + currPage;
	}

	// Bad design as the Book class is having two responsibilities to print in HTML
	// as well as Notepad.
//	In future it can have another print request like print in MS Word, then we would be changing the class.
	public String printPageInNotepad() {
		return "Printing page in notepad " + currPage;
	}
}
