package com.uav.flight.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class Flight {
    private long Number;
    private List<WayPoint> waypoints;
    private List<TemporaryPoint> passedPoints;
}
