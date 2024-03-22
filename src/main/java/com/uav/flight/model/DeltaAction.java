package com.uav.flight.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeltaAction {
    private double SpeedDelta;
    private double AltitudeDelta;
    private double DegreeDelta;
}
