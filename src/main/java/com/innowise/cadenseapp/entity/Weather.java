package com.innowise.cadenseapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity(name="Weather")
@Table(name="weather")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Weather {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp timeOfRecordCreation;

    @Column(name = "name_of_city")
    private String nameOfCity;

    @Column(name = "temperature")
    private Float temperature;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Weather weather = (Weather) o;
        return id != null && Objects.equals(id, weather.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
