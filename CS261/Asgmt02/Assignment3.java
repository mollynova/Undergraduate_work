import java.io.PrintStream;
import java.util.Collection;

import library.Item;
import library.Library;

// make no changes to this code,
//   except to have it print out your actual name instead of "I. Forgot"

public class Assignment3
{
	private static Library library = new Library();
	
	public static void main(String[] args)
	{
		PrintStream	out = System.out;				// we will be printing to the standard output stream
		Item		item;
		
		out.printf("CS261 - Assignment 3 - I. Forgot%n%n");
		
		// add items to library
		out.printf(">>>>>> adding items to library:%n%n");
		item = library.addBook("The Curious Incident of the Dog in the Night-Time", "Mark Haddon", 240, "autism", "Asperger's Syndrome");
		if (item != null)
			library.printItem(out, item);
		item = library.addBook("Starship Troopers", "Robert A. Heinlein", 325, "science fiction", "war", "weapons");
		if (item != null)
			library.printItem(out, item);
		item = library.addBook("The Moon Is A Harsh Mistress", "Robert A. Heinlein", 389, "science fiction", "moon", "social structures");
		if (item != null)
			library.printItem(out, item);
		item = library.addBook("Fear and Loathing in Las Vegas", "Hunter S. Thompson", 224, "sixties", "gonzo", "drugs");
		if (item != null)
			library.printItem(out, item);
		item = library.addMusicAlbum("Europe In '72", "Grateful Dead", 12, "acid rock", "sixties", "jam bands", "cosmic");
		if (item != null) {
			library.addBandMembers(item, "Jerry Garcia", "Bill Kreutzman", "Keith Godcheaux", "Donna Godcheaux", "Phil Lesh", "Bob Weir", "Mickey Hart");
			library.printItem(out, item);
			}
		item = library.addMusicAlbum("Steal Your Face", "Grateful Dead", 12, "acid rock", "sixties", "jam bands", "cosmic");
		if (item != null) {
			library.addBandMembers(item, "Jerry Garcia", "Bill Kreutzman", "Keith Godcheaux", "Donna Godcheaux", "Phil Lesh", "Bob Weir", "Mickey Hart");
			library.printItem(out, item);
			}
		item = library.addMusicAlbum("From the Mars Hotel", "Grateful Dead", 12, "acid rock", "sixties", "jam bands", "cosmic");
		if (item != null) {
			library.addBandMembers(item, "Jerry Garcia", "Bill Kreutzman", "Keith Godcheaux", "Donna Godcheaux", "Phil Lesh", "Bob Weir", "Mickey Hart");
			library.printItem(out, item);
			}
		item = library.addMusicAlbum("Don't Let Go", "Jerry Garcia Band", 15, "acid rock", "jam bands");
		if (item != null) {
			library.addBandMembers(item, "Jerry Garcia", "Keith Godcheaux", "Donna Godcheaux", "John Kahn");
			library.printItem(out, item);
			}
		item = library.addMusicAlbum("Time Out", "Dave Brubeck Quartet", 7, "jazz", "unusual time signatures", "fifties");
		if (item != null) {
			library.addBandMembers(item, "Dave Brubeck", "Paul Desmond", "Joe Morello", "Eugene Wright");
			library.printItem(out, item);
			}
		item = library.addMusicAlbum("Newborn Light", "Urszula Dudziak", 7, "jazz", "scat singing", "cosmic");
		if (item != null) {
			library.addBandMembers(item, "Urszula Dudziak", "Adam Makowicz");
			library.printItem(out, item);
			}
		item = library.addMovie("Lost In Translation", "Sofia Coppola", 14, "Japan", "loneliness", "time");
		if (item != null) {
			library.addCast(item, "Bill Murray", "Scarlett Johansson");
			library.printItem(out, item);
			}
		item = library.addMovie("Groundhog Day", "Harold Ramis", 14, "newscaster", "groundhog", "time");
		if (item != null) {
			library.addCast(item, "Bill Murray", "Andie MacDowell");
			library.printItem(out, item);
			}
		item = library.addMovie("Monterey Pop", "D.A. Pennebaker", 14, "sixties", "rock", "concert");
		if (item != null) {
			library.addCast(item, "Jimi Hendrix", "Janis Joplin", "Hugh Masakela", "Jefferson Airplane", "Country Joe and the Fish");
			library.printItem(out, item);
			}
				
		out.printf(">>>>>> printing items from library%n%n");

		// print books, music albums, movies
		out.printf(">>> books:%n%n");
		printItems(out, library.books());
		out.printf(">>> music albums:%n%n");
		printItems(out, library.musicAlbums());
		out.printf(">>> movies:%n%n");
		printItems(out, library.movies());
		
		// print items for keyword
		printItemsForKeyword(out, "science fiction");
		printItemsForKeyword(out, "jazz");
		printItemsForKeyword(out, "cosmic");
		printItemsForKeyword(out, "autism");
		printItemsForKeyword(out, "sixties");
		printItemsForKeyword(out, "time");
		printItemsForKeyword(out, "fifties");
		printItemsForKeyword(out, "forties");
		
		// items by artist
		out.printf(">>> books by Robert A. Heinlein:%n%n");
		printItems(out, library.booksByAuthor("Robert A. Heinlein"));
		out.printf(">>> books by Mark Haddon:%n%n");
		printItems(out, library.booksByAuthor("Mark Haddon"));
		out.printf(">>> music by the Grateful Dead:%n%n");
		printItems(out, library.musicByBand("Grateful Dead"));
		out.printf(">>> music by Kiss:%n%n");
		printItems(out, library.musicByBand("Kiss"));
		out.printf(">>> movies by Sofia Coppola:%n%n");
		printItems(out, library.moviesByDirector("Sofia Coppola"));
		out.printf(">>> music by Jerry Garcia:%n%n");
		printItems(out, library.musicByMusician("Jerry Garcia"));
		out.printf(">>> movies with Bill Murray:%n%n");
		printItems(out, library.moviesByActor("Bill Murray"));
		
		out.printf(">>>>>> removing items from library%n%n");
		removeBook(out, "The Curious Incident of the Dog in the Night-Time");
		removeMusicAlbum(out, "From the Mars Hotel");
		removeMovie(out, "Lost In Translation");
		printItemsForKeyword(out, "autism");
		printItemsForKeyword(out, "sixties");
		printItemsForKeyword(out, "time");
		out.printf(">>> books by Mark Haddon:%n%n");
		printItems(out, library.booksByAuthor("Mark Haddon"));
		out.printf(">>> music by the Grateful Dead:%n%n");
		printItems(out, library.musicByBand("Grateful Dead"));
		out.printf(">>> movies with Bill Murray:%n%n");
		printItems(out, library.moviesByActor("Bill Murray"));
	}

	private static void removeBook(PrintStream out, String title)
	{
		if (library.removeBook(title)) {
			out.printf(">>> removed \"%s\"%n%n", title);
			out.printf(">>> books:%n%n");
			printItems(out, library.books());
			}
		else
			out.printf(">>> could not remove %s%n%n", title);
	}
	
	private static void removeMusicAlbum(PrintStream out, String title)
	{
		if (library.removeMusicAlbum(title)) {
			out.printf(">>> removed \"%s\"%n%n", title);
			out.printf(">>> music albums:%n%n");
			printItems(out, library.musicAlbums());
			}
		else
			out.printf(">>> could not remove %s%n%n", title);
	}
	
	private static void removeMovie(PrintStream out, String title)
	{
		if (library.removeMovie(title)) {
			out.printf(">>> removed \"%s\"%n%n", title);
			out.printf(">>> movies:%n%n");
			printItems(out, library.movies());
			}
		else
			out.printf(">>> could not remove %s%n%n", title);
	}
	
	private static void printItemsForKeyword(PrintStream out, String keyword)
	{
		Collection<Item>	items;

		out.printf(">>> items for keyword: %s%n%n", keyword);
		items = library.itemsForKeyword(keyword);
		printItems(out, items);
	}
	
	private static void printItems(PrintStream out, Collection<Item> items)
	{
		if (items != null && items.size() > 0)
			for (Item item : items)
				library.printItem(out, item);
		else
			out.printf("none%n%n");
	}
}
