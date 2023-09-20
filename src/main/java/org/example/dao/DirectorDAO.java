package org.example.dao;

import org.example.model.Director;
import org.example.model.Movie;
import org.hibernate.Session;

import java.util.List;

public class DirectorDAO {

    public static void getDirector(Session session, int id) {
        Director director = session.get(Director.class, id);
        System.out.println("Director received from DB is " + director);
    }

    public static void getMovieList(Session session, int id) {
        Director director = session.get(Director.class, id);
        List<Movie> movieList = director.getMovies();
        System.out.println("A list of films by a specified director:");
        movieList.forEach(movie -> System.out.println(movie));
    }

    public static void changeDirector(Session session, int director_id, int movie_id) {
        Director director = session.get(Director.class, director_id);
        Movie movie = session.get(Movie.class, movie_id);
        // for hibernate cash
        movie.getDirector().getMovies().remove(movie);
        // SQL
        movie.setDirector(director);
        director.getMovies().add(movie);
        System.out.println(movie + " changed director to " + director);
    }

}
