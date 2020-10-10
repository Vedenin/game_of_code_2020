package eu.gameofcode.endgame.transportapi.repository;

import eu.gameofcode.endgame.transportapi.model.Calendar;
import eu.gameofcode.endgame.transportapi.model.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, String> {
}

