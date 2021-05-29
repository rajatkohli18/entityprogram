package model;

public class Author implements Comparable<Author>{
	private int id;
	private String name;
	private int bookid;
	

	public Author() {
		super();
	
	}


	public Author(int id, String name, int bookid) {
		super();
		this.id = id;
		this.name = name;
		this.bookid = bookid;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getBookid() {
		return bookid;
	}


	public void setBookid(int bookid) {
		this.bookid = bookid;
	}


	public int compareTo(Author au){  
		if(bookid==au.bookid)  
		return 0;  
		else if(bookid>au.bookid)  
		return 1;  
		else  
		return -1;  
		}  
}
