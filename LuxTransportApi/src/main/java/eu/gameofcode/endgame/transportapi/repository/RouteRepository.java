package eu.gameofcode.endgame.transportapi.repository;

import eu.gameofcode.endgame.transportapi.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, String> {
}

