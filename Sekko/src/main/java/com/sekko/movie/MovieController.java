package com.sekko.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {
	@Autowired
	MovieService movieService;
	
	@RequestMapping(value={"", "/"}, method = RequestMethod.GET)
	public Iterable<Movie> getAllMovie() {
		return movieService.listMovies();
	}
}
