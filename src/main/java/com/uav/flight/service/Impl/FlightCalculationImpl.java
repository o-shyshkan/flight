package com.uav.flight.service.Impl;

import com.uav.flight.model.DeltaAction;
import com.uav.flight.model.TemporaryPoint;
import com.uav.flight.model.AirplaneCharacteristics;
import com.uav.flight.model.WayPoint;
import com.uav.flight.service.FlightCalculation;
import com.uav.flight.service.LocationCalculation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightCalculationImpl implements FlightCalculation {

    private final LocationCalculation locationCalculation;

    @Override
    public TemporaryPoint getNewWPByDelta(TemporaryPoint currentTmpWp,
                                          DeltaAction deltaAction,
                                          AirplaneCharacteristics airplaneCharacteristics,
                                          long timeIntervalSeconds) {

        double altitude = getIncreaseByVelocity(currentTmpWp.getAltitude(),
                airplaneCharacteristics.getAltitudeSpeed(), timeIntervalSeconds, deltaAction.getAltitudeDelta());
        double speed = getIncreaseByVelocity(currentTmpWp.getSpeed(),
                airplaneCharacteristics.getVelocity(), timeIntervalSeconds, deltaAction.getSpeedDelta());
        double velocity = (deltaAction.getSpeedDelta() == 0) ? 0 : airplaneCharacteristics.getVelocity();
        double degree = getIncreaseByVelocity(currentTmpWp.getDegree(),
                airplaneCharacteristics.getDegreeSpeed(), timeIntervalSeconds, deltaAction.getDegreeDelta());
        double[] nextPoint = locationCalculation.getNextLocation(currentTmpWp, velocity,1);
        return new TemporaryPoint(nextPoint[0], nextPoint[1], altitude, speed, degree);
    }

    @Override
    public boolean matchWP(TemporaryPoint currentTmpWp, WayPoint wp, long timeInterval) {

        double distanceToWp = locationCalculation.calculateDistance(
                currentTmpWp.getLatitude(), currentTmpWp.getLongitude(),
                wp.getLatitude(), wp.getLongitude());
        return Math.abs(distanceToWp) <= currentTmpWp.getSpeed() * timeInterval;
    }

    private double getIncreaseByVelocity(double value, double velocity, long timeIntervalSeconds, double delta) {

        if (Math.abs(delta) <= velocity * timeIntervalSeconds) {
            return value + delta;
        }
        if (delta > 0) {
            value = value + velocity * timeIntervalSeconds;
        } else {
            value = value - velocity * timeIntervalSeconds;
        }
        return value;
    }
}
