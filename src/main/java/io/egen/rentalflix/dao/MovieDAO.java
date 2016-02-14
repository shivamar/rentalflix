package io.egen.rentalflix.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import io.egen.rentalflix.Movie;
import io.egen.rentalflix.datastore.MovieDB;

public class MovieDAO {
	
	public List<Movie> findAll() {	
		Collection<Movie> mv = MovieDB.id_Movie_Map.values();	
		List<Movie> mvList = new ArrayList<>();
		
		Iterator<Movie> itr = mv.iterator();
		
		while(itr.hasNext())
		{
			mvList.add(itr.next());
		}
				
		return mvList;
	}

	public Movie create(Movie movie) {
		String id = movie.getId();
		
		if(MovieDB.id_Movie_Map.get(id) == null){
			MovieDB.id_Movie_Map.putIfAbsent(id, movie);
		}
		return movie;
	}

	//returns updated movie or null if movie is not present
	public Movie update(Movie movie) {
		String id = movie.getId();
		Movie mv = MovieDB.id_Movie_Map.get(id);		
		if(mv == null) return null;
				
		MovieDB.id_Movie_Map.put(id, movie);		
		return movie;				
	}

	public Movie delete(String id) {
		Movie mv = MovieDB.id_Movie_Map.remove(id);
		return mv;
	}
	
	public void clear(){
		
		MovieDB.id_Movie_Map.clear();
	}
	
}
