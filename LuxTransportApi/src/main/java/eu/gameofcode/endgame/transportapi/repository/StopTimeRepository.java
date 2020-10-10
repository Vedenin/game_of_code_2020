package eu.gameofcode.endgame.transportapi.repository;

import eu.gameofcode.endgame.transportapi.model.StopTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StopTimeRepository extends JpaRepository<StopTime, Integer> {
}

