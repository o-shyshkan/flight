package com.uav.flight.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TemporaryPoint {
    private double latitude;
    private double longitude;
    private double altitude;
    private double speed;
    private double degree;
}
