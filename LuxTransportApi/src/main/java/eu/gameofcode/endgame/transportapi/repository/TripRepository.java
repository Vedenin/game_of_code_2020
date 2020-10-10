package eu.gameofcode.endgame.transportapi.repository;

import eu.gameofcode.endgame.transportapi.model.Route;
import eu.gameofcode.endgame.transportapi.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, String> {
}

