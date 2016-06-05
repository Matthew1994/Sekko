package com.sekko.cinema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Cinema {
	@NotNull
	@Column(nullable=false, unique=true)
	private String name;
	
	@NotNull
	@Column(nullable=false)
	private String img;
	
	@NotNull
	@Column(nullable=false, unique=true)
	private String location;
	
	private String transport;
	
	private String movies;
	
	@NotNull
	@Column(nullable=false, unique=true)
	private String url;
	
	@Id
	@NotNull
	private long id;
	
	public String getUrl() {
		return url;
	}
	
	public String getMovies() {
		return movies;
	}
	
	public long getId() {
		return id;
	}
	
	public String getLocation() {
		return location;
	}
	
	public String getName() {
		return name;
	}
	
	public String getTransport() {
		return transport;
	}
	
	public String getImg() {
		return img;
	}
}
