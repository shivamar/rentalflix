package io.egen.rentalflix.datastore;

import io.egen.rentalflix.Movie;
import java.util.concurrent.ConcurrentHashMap;

public final class MovieDB {	//
	public static volatile ConcurrentHashMap<String,Movie> id_Movie_Map = new ConcurrentHashMap<String,Movie>();	
}