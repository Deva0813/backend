package org.filmrental.com.utility;

import org.filmrental.com.model.MovieRating;

public class MovieRatingValidation {
	public static boolean isValidRating(String rating) {
        try {
            MovieRating.valueOf(rating);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
