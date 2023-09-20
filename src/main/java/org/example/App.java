package org.example;

import org.example.dao.CommonDAO;
import org.example.dao.DirectorDAO;
import org.example.dao.MovieDAO;
import org.example.model.Director;
import org.example.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Создать Java приложение с подключенным Hibernate, которое имеет две
 * сущности - Режиссер и Фильм. Эти две сущности связаны с таблицами в
 * БД.
 */
public class App {
    public static void main( String[] args ) {
        Configuration configuration = new Configuration().addAnnotatedClass(Director.class)
                .addAnnotatedClass(Movie.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        DirectorDAO directorDAO = new DirectorDAO();
        MovieDAO movieDAO = new MovieDAO();
        CommonDAO commonDAO = new CommonDAO();

        try {
            session.beginTransaction();

            // Get director by ID=1
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            directorDAO.getDirector(session, 1);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            // Get a list of films by a specified director
            directorDAO.getMovieList(session, 1);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            // Get the director of the specified movie
            movieDAO.getMovieDirector(session, 5);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            // Add movie by director with id=3 to the DB
//            movieDAO.addMovie(session, 3);
//            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            // Add new director and new film to the DB
//            Director director = new Director("Test director", 30);
//            Movie movie = new Movie(director,"Test movie", 2023);
//            commonDAO.addDirectorAndFilm(session, director, movie);
//            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            // Change the director of a movie
//            directorDAO.changeDirector(session, 1, 14);
//            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            // Delete a movie by any director
//            movieDAO.removeMovie(session, 1, 14);
//            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }


}
