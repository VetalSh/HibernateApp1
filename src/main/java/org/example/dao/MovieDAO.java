package org.example.dao;

import org.example.model.Director;
import org.example.model.Movie;
import org.hibernate.Session;

public class MovieDAO {

    public static void getMovieDirector(Session session, int id) {
        Movie movie = session.get(Movie.class, id);
        System.out.print("The director of movie " + movie.getMovieName() + " is ");
        Director director = movie.getDirector();
        System.out.println(director);
    }

    public static void addMovie(Session session, int director_id) {
        Director director = session.get(Director.class, director_id);
        Movie newMovie = new Movie(director, "Sherlock Holmes: A Game of Shadows", 2011);
        // for Hibernate cash
        director.getMovies().add(newMovie);
        // make SQL request
        session.save(newMovie);
        System.out.println(newMovie + " by " + director + " successfully added to the database.");
    }

    public static void removeMovie(Session session, int director_id, int movie_id) {
        Director director = session.get(Director.class, director_id);
        director.getMovies().forEach(movie -> {
            if (movie.getMovieId() == movie_id) {
                // SQL
                session.remove(movie);
                // does not make SQL query, but it is necessary for correct Hibernate cash state
                movie.getDirector().setMovies(null);
                System.out.println("The movie has been successfully removed from the database.");
            } else {
                System.out.println("Can't find a movie for deleting");
            }
        });
    }
}
