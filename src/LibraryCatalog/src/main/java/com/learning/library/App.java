package com.learning.library;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Welcome to the Library Catalog" );
        
        List<Book> books = generateBooks();
        
        System.out.println("List of Books");
        
        //Print using standard for loop
        for(Book book: books)
        {
        	System.out.println(book);
        }
        
        System.out.println("\n\nPrint list using Streams");
        //Print using Streams
        books.stream()
        	 .sorted()
        	 .forEach(System.out::println);
    }
    
    public static List<Book> generateBooks()
    {
    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    	
    	List<Book> books = new ArrayList<Book>();
    	
    	try
		{
			books.add(new Book("Game of Thrones", "978-0553897845", "George R. R. Martin", "Fantasy", formatter.parse("01/08/1996")));
			books.add(new Book("A Clash of Kings", "978-0553579901", "George R. R. Martin", "Fantasy", formatter.parse("05/09/2000")));
			books.add(new Book("A Storm of Swords", "978-0553573428", "George R. R. Martin", "Fantasy", formatter.parse("04/03/2003")));
			books.add(new Book("A Feast for Crows", "978-0553582024", "George R. R. Martin", "Fantasy", formatter.parse("26/09/2006")));
			books.add(new Book("A Dance with Dragons", "978-0553582017", "George R. R. Martin", "Fantasy", formatter.parse("29/10/2013")));
		} 
    	catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return books;
    }
}
