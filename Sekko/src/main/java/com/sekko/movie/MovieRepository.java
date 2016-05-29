package com.sekko.movie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository  extends JpaRepository<Movie, Long>{
	Movie findById(long id);
	Movie findByName(String name);
}
