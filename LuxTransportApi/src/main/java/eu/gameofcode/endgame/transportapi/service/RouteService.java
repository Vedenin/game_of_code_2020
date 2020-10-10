package eu.gameofcode.endgame.transportapi.service;

import eu.gameofcode.endgame.transportapi.repository.RouteRepository;
import org.springframework.stereotype.Service;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }
}
