package com.uav.flight.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
public class Airplane {
    private long Id;
    private AirplaneCharacteristics airplaneCharacteristics;
    private TemporaryPoint position;
    private List<Flight> flights;
}
