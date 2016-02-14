package io.egen.rentalflix;
import io.egen.rentalflix.dao.MovieDAO;
import io.egen.rentalflix.datastore.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Service implementing IFlix interface
 * You can use any Java collection type to store movies
 */
public class MovieService implements IFlix {
	static MovieService movieService;
	MovieDAO movieDAO = new MovieDAO();
	
	private MovieService(){
		
	}
	
	//returns a singleton class instance
	public static MovieService getInstance(){
		if(movieService == null)
			movieService = new MovieService();
		
		return movieService;
	}

	/**
	 * Finds all available movies in the movie store
	 * @return list of movies or empty list
	 */
	@Override
	public List<Movie> findAll() {	
		List<Movie> mvList = movieDAO.findAll();							
				
		return mvList;
	}

	@Override
	public List<Movie> findByName(String name) {
		List<Movie> mvList = movieDAO.findAll();
		List<Movie> result = new ArrayList<>();
		
		for(Movie movie : mvList)
		{
			if(movie.getTitle().equals(name)){
				result.add(movie);
			}
		}
		
		return result;
	}

	@Override
	public Movie create(Movie movie) {
		
		return movieDAO.create(movie);
	}

	@Override
	public Movie update(Movie movie) throws IllegalArgumentException{
		Movie mv = movieDAO.update(movie);
		
		if(mv == null){
			throw new IllegalArgumentException("No Movie with this ID found");
		}
		
		return mv;
	}

	@Override
	public Movie delete(String id) throws IllegalArgumentException{		
		Movie mv = movieDAO.delete(id);
		
		if(mv == null)
		{
			throw new IllegalArgumentException("No Movie with this ID found");
		}
		
		return mv;
	}

	@Override
	public boolean rentMovie(String movieId, String user) throws IllegalArgumentException
	{
		Movie mv = MovieDB.id_Movie_Map.get(movieId);
		
		if(mv == null)
		{
			throw new IllegalArgumentException("No Movie with this ID found.");
		}
		
		if(mv.getUser() != "")
		{
			throw new IllegalArgumentException("Movie is already rented.");
		}
		
		mv.setUser(user);		
		return true;
	}

	public void clear() {		
		movieDAO.clear();
	}
}
