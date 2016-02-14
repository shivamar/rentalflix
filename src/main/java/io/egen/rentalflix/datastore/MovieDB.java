package io.egen.rentalflix.datastore;

import io.egen.rentalflix.Movie;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class MovieDB {
	//static MovieDB mv;
	public static volatile ConcurrentHashMap<String,Movie> id_Movie_Map = new ConcurrentHashMap<String,Movie>();
	//public static volatile ConcurrentHashMap<String,Set<String>> movieNameIDMap = new ConcurrentHashMap<>();}
}