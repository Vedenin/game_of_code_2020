package eu.gameofcode.endgame.transportapi.repository;

import eu.gameofcode.endgame.transportapi.model.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StopRepository extends JpaRepository<Stop, String> {
}

