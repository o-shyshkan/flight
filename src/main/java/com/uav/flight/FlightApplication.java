package com.uav.flight;

import com.uav.flight.model.Airplane;
import com.uav.flight.model.AirplaneCharacteristics;
import com.uav.flight.model.Flight;
import com.uav.flight.model.TemporaryPoint;
import com.uav.flight.model.WayPoint;
import com.uav.flight.service.PlaneCalculation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class FlightApplication implements CommandLineRunner {

	private final PlaneCalculation planeCalculation;

    public FlightApplication(PlaneCalculation planeCalculation) {
        this.planeCalculation = planeCalculation;
    }

    public static void main(String[] args) {
		SpringApplication.run(FlightApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Airplane airplane = new Airplane();
		AirplaneCharacteristics airplaneCharacteristics = new AirplaneCharacteristics(15, 2, 10, 30);
		List<Flight> flights = new ArrayList<>();
		ArrayList<WayPoint> wayPoints = new ArrayList<>();
		wayPoints.add(new WayPoint(48.46648208170979,35.07334458648163,0,1));
		wayPoints.add(new WayPoint(48.46856997194599,35.07154750343988,30,15));
		wayPoints.add(new WayPoint(48.46993027535855,35.076296287729505,50,15));
		wayPoints.add(new WayPoint(48.47201150407866,35.0817624011515,40,5));

		List<TemporaryPoint> points = planeCalculation.calculateRoute(airplaneCharacteristics, wayPoints);
		Flight flight = new Flight(1, wayPoints, points);
		flights.add(flight);
		airplane.setFlights(flights);
		airplane.setAirplaneCharacteristics(airplaneCharacteristics);
	}
}
