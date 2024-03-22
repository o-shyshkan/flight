package com.uav.flight.service;

import com.uav.flight.model.DeltaAction;
import com.uav.flight.model.TemporaryPoint;
import com.uav.flight.model.WayPoint;

public interface DeltaCalculation {

    DeltaAction getDelta(TemporaryPoint currentTmpWp, WayPoint wp);
}
