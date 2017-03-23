package com.learning.library;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Book implements Comparable<Book>
{
	//Constants
	private final String TITLE;
	private final String ISBN;
	private final String AUTHOR;
	private final String GENRE;
	private final Date PUBLISHEDDATE;
	
	private boolean isCheckedOut;
	private boolean isReserved;
	
	public Book(String tITLE, String iSBN, String aUTHOR, String gENRE, Date pUBLISHEDDATE)
	{
		super();
		TITLE = tITLE;
		ISBN = iSBN;
		AUTHOR = aUTHOR;
		GENRE = gENRE;
		PUBLISHEDDATE = pUBLISHEDDATE;
	}
	
	public String toString()
	{
		StringBuffer buff = new StringBuffer("");
		
		buff.append("\nTitle:\t\t" +TITLE)
			.append("\nISBN:\t\t" +ISBN)
			.append("\nAuthor:\t\t" +AUTHOR)
			.append("\nGenre:\t\t" +GENRE)
			.append("\nPublished:\t" +new SimpleDateFormat().format(PUBLISHEDDATE));
		
		return buff.toString();
	}
	
	public int compareTo(Book b)
	{
		return b.PUBLISHEDDATE.compareTo(this.getPUBLISHEDDATE());
	}
	

	public boolean isCheckedOut()
	{
		return isCheckedOut;
	}

	public void setCheckedOut(boolean isCheckedOut)
	{
		this.isCheckedOut = isCheckedOut;
	}

	public boolean isReserved()
	{
		return isReserved;
	}

	public void setReserved(boolean isReserved)
	{
		this.isReserved = isReserved;
	}

	public String getTITLE()
	{
		return TITLE;
	}

	public String getISBN()
	{
		return ISBN;
	}

	public String getAUTHOR()
	{
		return AUTHOR;
	}

	public String getGENRE()
	{
		return GENRE;
	}

	public Date getPUBLISHEDDATE()
	{
		return PUBLISHEDDATE;
	}
	
}
