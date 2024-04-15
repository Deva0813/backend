package org.filmrental.com.dto;

//public interface CountFilms{
//	Integer getReleaseYear();
//	int getNumberOfFilms();
//}

public record CountFilms(Integer releaseYear, Long numberOfFilms) {
	
}

