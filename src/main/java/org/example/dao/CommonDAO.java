package org.example.dao;

import org.example.model.Director;
import org.example.model.Movie;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Collections;

public class CommonDAO {

    public static void addDirectorAndFilm(Session session, Director director, Movie movie) {
        // for hibernate cash
        director.setMovies(new ArrayList<>(Collections.singletonList(movie)));
        // make SQL request
        session.save(director);
        session.save(movie);
        System.out.println("Movie " + movie + " by " + director + " successfully added to the database.");
    }
}
