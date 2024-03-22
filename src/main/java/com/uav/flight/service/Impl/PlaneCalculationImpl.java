package com.uav.flight.service.Impl;

import com.uav.flight.model.AirplaneCharacteristics;
import com.uav.flight.model.TemporaryPoint;
import com.uav.flight.model.WayPoint;
import com.uav.flight.model.DeltaAction;
import com.uav.flight.service.DeltaCalculation;
import com.uav.flight.service.FlightCalculation;
import com.uav.flight.service.LocationCalculation;
import com.uav.flight.service.PlaneCalculation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaneCalculationImpl implements PlaneCalculation {

    public static final int TIME_INTERVAL_SECONDS = 1;
    private final DeltaCalculation deltaCalculation;
    private final FlightCalculation flightCalculation;
    private final LocationCalculation locationCalculation;

    @Override
    public List<TemporaryPoint> calculateRoute(AirplaneCharacteristics characteristics,
                                               List<WayPoint> wayPoints) {
        TemporaryPoint currentTmpPoint = null;
        List<TemporaryPoint> tmpPoints = new ArrayList<>();
        int wpCount = 0;
        double distanceToWp;
        for(WayPoint wp : wayPoints) {
            wpCount++;
            if (wpCount == 1) {
                currentTmpPoint = new TemporaryPoint(wp.getLatitude(), wp.getLongitude(),
                        wp.getAltitude(), wp.getSpeed(),0);
                continue;
            }
            while (!flightCalculation.matchWP(currentTmpPoint, wp, TIME_INTERVAL_SECONDS)) {
                DeltaAction deltaAction = deltaCalculation.getDelta(currentTmpPoint, wp);
                TemporaryPoint tmpPoint = flightCalculation.getNewWPByDelta(currentTmpPoint,
                        deltaAction, characteristics, TIME_INTERVAL_SECONDS);
                tmpPoints.add(tmpPoint);
                distanceToWp = locationCalculation.calculateDistance(
                        tmpPoint.getLatitude(), tmpPoint.getLongitude(),
                        wp.getLatitude(), wp.getLongitude());
                System.out.println("WP:" + wpCount + " distanceToWp:" + distanceToWp +
                        " tmpPoint:" + tmpPoint.toString());
                currentTmpPoint = tmpPoint;
            }

        }
        return tmpPoints;
    }
}
