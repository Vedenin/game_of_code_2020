package eu.gameofcode.endgame.transportapi.data;

import eu.gameofcode.endgame.transportapi.model.Route;
import eu.gameofcode.endgame.transportapi.model.Stop;
import eu.gameofcode.endgame.transportapi.model.StopTime;
import eu.gameofcode.endgame.transportapi.model.Trip;
import eu.gameofcode.endgame.transportapi.repository.RouteRepository;
import eu.gameofcode.endgame.transportapi.repository.StopRepository;
import eu.gameofcode.endgame.transportapi.repository.StopTimeRepository;
import eu.gameofcode.endgame.transportapi.repository.TripRepository;
import eu.gameofcode.endgame.transportapi.service.FileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class DataInitializator {

    @Autowired
    private FileReader fileReader;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private StopRepository stopRepository;
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private StopTimeRepository stopTimeRepository;

    public void initializeData() {
        readRoutes();
        readStops();
        readTrips();
        readStopTimes();
    }

    private void readRoutes() {
        try {
            List<String> routesLines =  fileReader.getFileLines("scheduledata/routes.txt");
            for (int i=1; i< routesLines.size(); i++) {
                String routeAttr [] = routesLines.get(i).split(",");

                Route route = new Route();
                route.setId(routeAttr[0]);
                route.setShortName(routeAttr[2]);
                routeRepository.save(route);
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readStops() {
        List<String> stopLines = null;
        try {
            stopLines = fileReader.getFileLines("scheduledata/stops.txt");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i=1; i< stopLines.size(); i++) {
            String attr [] = stopLines.get(i).replace("\"","").split(",");

            Stop stop = new Stop();
            stop.setId(attr[0]);
            stop.setName(attr[2] + ", " + attr[3]);
            stop.setLat(attr[5]);
            stop.setLon(attr[6]);

            stopRepository.save(stop);
        }
    }

    private void readTrips() {
        List<String> tripLines = null;
        try {
            tripLines = fileReader.getFileLines("scheduledata/trips.txt");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i=1; i< tripLines.size(); i++) {
            String attr [] = tripLines.get(i).replace("\"","").split(",");

            Trip trip = new Trip();
            trip.setTripId(attr[2]);
            Route route = routeRepository.findById(attr[0]).get();
            trip.setRoute(route);
            trip.setTripHeadSigh(attr[3] + " " + attr[4]);
            trip.setRouteName(route.getShortName());

            tripRepository.save(trip);
        }
    }

    private void readStopTimes() {
        List<String> stopTimesLines = null;
        try {
            stopTimesLines = fileReader.getFileLines("scheduledata/stop_times.txt");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i=1; i< stopTimesLines.size(); i++) {
            String attr [] = stopTimesLines.get(i).replace("\"","").split(",");

            Trip trip = tripRepository.findById(attr[0]).get();
            StopTime stopTime = new StopTime();
            stopTime.setTripHeadSign(trip.getTripHeadSigh());
            stopTime.setRoutName(trip.getRouteName());
            stopTime.setStop(stopRepository.findById(attr[3]).get());

            String arrival = attr[1].length() > 7 ? attr[1] : "0"+attr[1];
            String depart = attr[2].length() > 7 ? attr[2] : "0"+attr[2];
            Integer arrivalHours = Integer.valueOf(arrival.substring(0,2));
            arrival = arrivalHours < 24 ? arrival : "0" + (arrivalHours - 24) + arrival.substring(2);
            Integer departHours = Integer.valueOf(depart.substring(0,2));
            depart = departHours < 24 ? depart : "0" + (departHours - 24) + depart.substring(2);

            stopTime.setArrivalTime(LocalTime.parse(arrival));
            stopTime.setDepartureTime(LocalTime.parse(depart));

            stopTimeRepository.save(stopTime);
        }
    }
}
