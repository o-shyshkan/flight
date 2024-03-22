package com.uav.flight.service;

import com.uav.flight.model.TemporaryPoint;

public interface LocationCalculation {

     double[] getNextLocation(TemporaryPoint temporaryPoint, double velocity, long deltaTime);

     double calculateDistance(double lat1, double lon1, double lat2, double lon2);
}
