package br.eti.gadelha.nutrition.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.awt.*;

@Audited @Entity @Data @AllArgsConstructor @NoArgsConstructor
public class PointEntity extends GenericEntity {

    private Point point;
//    Point point = geometryFactory.createPoint( new Coordinate( 10, 5 ) );
//    event.setLocation( point )

//    CREATE TABLE vehicles (name VARCHAR, geom GEOGRAPHY(Point));
//    INSERT INTO vehicles VALUES ('Vehicle-1', 'POINT(44.34 82.96)');
}