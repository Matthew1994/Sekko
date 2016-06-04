package com.sekko.cinema;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
 
public interface CinemaRepository extends JpaRepository<Cinema, Long>{
	@Query(value="select * from cinema  where location regexp ?1", nativeQuery=true)
	Iterable<Cinema> findByQu(String qu);
	
	//这种原生的查询比封装好的查询更快
	@Query(value="select * from cinema", nativeQuery=true)
	Iterable<Cinema> findAllCinema();
	
	Cinema findById(long id);
	Cinema findByName(String name);
}
