package com.uav.flight.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WayPoint {
    private double latitude;
    private double longitude;
    private double altitude;
    private double speed;
}
