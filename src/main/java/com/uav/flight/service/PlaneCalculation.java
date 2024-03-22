package com.uav.flight.service;

import com.uav.flight.model.AirplaneCharacteristics;
import com.uav.flight.model.TemporaryPoint;
import com.uav.flight.model.WayPoint;
import java.util.List;

public interface PlaneCalculation {

    List<TemporaryPoint> calculateRoute (AirplaneCharacteristics characteristics,
                                         List<WayPoint> wayPoints);
}
