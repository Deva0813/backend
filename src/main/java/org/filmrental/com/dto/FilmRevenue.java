package org.filmrental.com.dto;

import java.math.BigDecimal;

import org.filmrental.com.model.Film;

public record FilmRevenue(Film film, BigDecimal revenue) {

}
