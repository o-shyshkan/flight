package com.uav.flight.service;

import com.uav.flight.model.AirplaneCharacteristics;
import com.uav.flight.model.DeltaAction;
import com.uav.flight.model.TemporaryPoint;
import com.uav.flight.model.WayPoint;

public interface FlightCalculation {

    TemporaryPoint getNewWPByDelta(TemporaryPoint currentTmpWp,
                                   DeltaAction deltaAction,
                                   AirplaneCharacteristics airplaneCharacteristics,
                                   long timeIntervalSeconds);

    boolean matchWP(TemporaryPoint currentTmpWp, WayPoint wp, long timeInterval);
}
