package com.sekko.times;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimesService {
	@Autowired
	private TimesRepository timesRepository;
	
	public TimesService() {
		super();
	}
	
	public Iterable<Times> listTimes() {
		return timesRepository.findAll();
	}
	

	//效率更高
	public Iterable<Times> getAllTimes() {
		return timesRepository.findAllTimes();
	}
	public Iterable<Times> getTimesByThree(String date, String cinemaId, String movieId) {
		return timesRepository.findByThree(date, cinemaId, movieId);
	}
	
	public Times getTimesById(String id) {
		return timesRepository.findById(id);
	}
}
