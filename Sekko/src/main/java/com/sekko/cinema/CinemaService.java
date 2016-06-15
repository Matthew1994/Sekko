package com.sekko.cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CinemaService {
	@Autowired
	private CinemaRepository cinemaRepository;
	
	public CinemaService() {
		super();
	}
	
	public Iterable<Cinema> listCinema() {
		return cinemaRepository.findAll();
	}
	
	public Cinema getCinemaById(long id) {
		return cinemaRepository.findById(id);
	}
	
	//效率更高
	public Iterable<Cinema> getAllCinema() {
		return cinemaRepository.findAllCinema();
	}
	//根据电影id，区名获取电影院列表
	public List<Cinema> getByMovieAndQu(String movieId, String qu) {
		Iterable<Cinema> cinemas = cinemaRepository.findByQu(qu);
		List<Cinema> temp = new ArrayList<Cinema>();
		for(Cinema cinema: cinemas) {
			String movies = cinema.getMovies();
			if (movies.indexOf(movieId) >= 0) {
				temp.add(cinema);
			}
		}
		return temp;
	}
	
	public Iterable<Cinema> getCinemaByQu(String qu) {
		return cinemaRepository.findByQu(qu);
	}
	
	public Iterable<Cinema> getCinemaByCinemaId(String cinemaId) {
		return cinemaRepository.findByCinemaId(cinemaId);
	}
}
