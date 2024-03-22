package com.uav.flight.service.Impl;

import com.uav.flight.model.TemporaryPoint;
import com.uav.flight.service.LocationCalculation;
import org.springframework.stereotype.Service;

@Service
public class LocationCalculationImpl implements LocationCalculation {
    // Earth radius in meters
    private static final double RADIUS_OF_EARTH = 6371000.0;
    // Convert degrees to radians
    private static double toRadians(double degrees) {
        return Math.toRadians(degrees);
    }
    // Convert radians to degrees
    private static double toDegrees(double radians) {
        return Math.toDegrees(radians);
    }

    @Override
    public double[] getNextLocation(TemporaryPoint temporaryPoint, double velocity, long deltaTime) {

        double Lat = temporaryPoint.getLatitude();
        double Lon = temporaryPoint.getLongitude();
        double speed = temporaryPoint.getSpeed();
        double direction = temporaryPoint.getDegree();
        double distance = speed * deltaTime + velocity * deltaTime*deltaTime / 2;
        double directionRad = toRadians(direction);
        // Convert latitude and longitude from degrees to radians
        double latRad = toRadians(Lat);
        double lonRad = toRadians(Lon);
        // Calculate the next latitude
        double nextLatRad = Math.asin(Math.sin(latRad) * Math.cos(distance / RADIUS_OF_EARTH) +
                Math.cos(latRad) * Math.sin(distance / RADIUS_OF_EARTH) * Math.cos(directionRad));
        double nextLat = toDegrees(nextLatRad);
        double nextLonRad = lonRad + Math.atan2(Math.sin(directionRad) * Math.sin(distance / RADIUS_OF_EARTH) * Math.cos(latRad),
                Math.cos(distance / RADIUS_OF_EARTH) - Math.sin(latRad) * Math.sin(nextLatRad));
        double nextLon = toDegrees(nextLonRad);
        return new double[]{nextLat, nextLon};
    }

    @Override
    public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return RADIUS_OF_EARTH * c;
    }
}
