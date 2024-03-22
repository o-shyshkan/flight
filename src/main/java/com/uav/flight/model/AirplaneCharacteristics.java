package com.uav.flight.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AirplaneCharacteristics {
    private float SpeedMax;
    private float Velocity;
    private float AltitudeSpeed;
    private float DegreeSpeed;
}
