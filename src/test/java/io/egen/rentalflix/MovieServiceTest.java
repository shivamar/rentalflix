package io.egen.rentalflix;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MovieServiceTest {
	MovieService  mvService;
	
	class MovieComparator implements Comparator<Movie>{		
		@Override
		public int compare(Movie arg0, Movie arg1) {				
			return arg0.getId().compareTo(arg1.getId());
		}		
	}
	
	@Before
	public void setUp() throws Exception {
		mvService = MovieService.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		mvService.clear();
	}

	@Test
	public void testFindAllCount() {	
		Movie movie1 = new Movie("Rocky 1",1999,"English");
		Movie movie2 = new Movie("Rocky 2",1999,"English");
		Movie movie3 = new Movie("Rocky 3",1999,"English");
		
		List<Movie> testList = new ArrayList<>();
		testList.add(movie1);
		testList.add(movie2);
		testList.add(movie3);
		mvService.create(movie1);
		mvService.create(movie2);
		mvService.create(movie3);
		
		List<Movie> list = mvService.findAll();
		
		assertEquals(list.size(), testList.size());
	}
	
	@Test
	public void testFindAllMovie() {	
		Movie movie1 = mvService.create(new Movie("Rocky 1",1999,"English"));
		Movie movie2 = mvService.create(new Movie("Rocky 2",1999,"English"));
		Movie movie3 = mvService.create(new Movie("Rocky 3",1999,"English"));	
		List<Movie> testList = new ArrayList<>();
		testList.add(movie1);
		testList.add(movie2);
		testList.add(movie3);
		
		List<Movie> list = mvService.findAll();

		Collections.sort(list, new MovieComparator());
		Collections.sort(testList, new MovieComparator());		
		assertTrue(list.equals(testList));
	}	

	@Test
	public void testFindByNameSize() {
		Movie movie1 = mvService.create(new Movie("Rocky 1",1999,"English"));
		List<Movie> testList = mvService.findByName("Rocky 1");
		
		assertEquals(1, testList.size());		
	}
	
	@Test
	public void testFindByName(){
		Movie movie1 = mvService.create(new Movie("Rocky 1",1999,"English"));
		List<Movie> testList = mvService.findByName("Rocky 1");
		
		assertEquals(movie1, testList.get(0));
	}

	@Test
	public void testCreateCorrect() {
		String name = "Rocky 1";
		int year = 1999;
		String lang="English";
		Movie movie1 = mvService.create(new Movie(name,year,lang));
		
		assertEquals(movie1.getTitle(), name);
		assertEquals(movie1.getYear(),year);
		assertEquals(movie1.getLanguage(),lang);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testUpdateException() {
		Movie movie2 = new Movie("Hitman", 2013, "English");
		mvService.update(movie2);
	}

	@Test
	public void testUpdate() {	
		String name = "Rocky 1";
		int year = 1999;
		String lang="English";
		Movie movie1 = mvService.create(new Movie(name,year,lang));
		
		String newName = "hitman";
		movie1.setTitle(newName);
		
		movie1 = mvService.update(movie1);		
		assertEquals(movie1.getTitle(), newName);
	}

	@Test
	public void testDelete() {
		String name = "Rocky 1";
		int year = 1999;
		String lang="English";
		Movie expected = mvService.create(new Movie(name,year,lang));		
		Movie result = mvService.delete(expected.getId());
		
		assertEquals(expected, result);				
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDeleteException() {	
		Movie movie1 = new Movie("HitMan",1999,"English");
		Movie result = mvService.delete(movie1.getId());
	}

	@Test
	public void testRentMovie() {
		String name = "Rocky 1";
		int year = 1999;
		String lang="English";
		Movie expected = mvService.create(new Movie(name,year,lang));	
		
		mvService.rentMovie(expected.getId(), "Shiva");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRentMovieException() {
		String name = "Rocky 1";
		int year = 1999;
		String lang="English";
		Movie expected = mvService.create(new Movie(name,year,lang));	
		
		mvService.rentMovie(expected.getId(), "Shiva");
		mvService.rentMovie(expected.getId(), "Shiva");
	}

}
