package com.uav.flight.service.Impl;

import com.uav.flight.model.DeltaAction;
import com.uav.flight.model.TemporaryPoint;
import com.uav.flight.model.WayPoint;
import com.uav.flight.service.DeltaCalculation;
import org.springframework.stereotype.Service;

@Service
public class DeltaCalculationImpl implements DeltaCalculation {

    @Override
    public DeltaAction getDelta(TemporaryPoint currentTmpWp, WayPoint wp) {
        double degreeDelta = getDegreeToWP(currentTmpWp, wp) - currentTmpWp.getDegree();
        double speedDelta = wp.getSpeed() - currentTmpWp.getSpeed();
        double altitudeDelta = wp.getAltitude() - currentTmpWp.getAltitude();
        return new DeltaAction(speedDelta,altitudeDelta,degreeDelta);
    }

    private double getDegreeToWP (TemporaryPoint currentTmpWp, WayPoint wp) {
        double lat1Rad = Math.toRadians(currentTmpWp.getLatitude());
        double lon1Rad = Math.toRadians(currentTmpWp.getLongitude());
        double lat2Rad = Math.toRadians(wp.getLatitude());
        double lon2Rad = Math.toRadians(wp.getLongitude());
        double dLon = lon2Rad - lon1Rad;
        // Calculate the bearing using the Haversine formula
        double y = Math.sin(dLon) * Math.cos(lat2Rad);
        double x = Math.cos(lat1Rad) * Math.sin(lat2Rad) - Math.sin(lat1Rad) * Math.cos(lat2Rad) * Math.cos(dLon);
        // Convert bearing from radians to degrees
        double bearing = Math.toDegrees(Math.atan2(y, x));
        // Normalize the bearing to a range between 0 and 360 degrees
        bearing = (bearing + 360) % 360;
        return bearing;
    }
}
