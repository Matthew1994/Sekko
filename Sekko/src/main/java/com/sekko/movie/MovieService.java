package com.sekko.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MovieService {
	@Autowired
	private MovieRepository movieRepository;
	
	public MovieService() {
		super();
	}
	
	public Iterable<Movie> listMovies() {
		return movieRepository.findAll();
	}
	
	public Movie getMovieById(long id) {
		return movieRepository.findById(id);
	}
	
	/*
	public Iterable<Movie> getMovieByType(String type) {
		Iterable<Movie> movies = movieRepository.findAll();
		movies.iterator().hasNext();
	}
	*/
}
