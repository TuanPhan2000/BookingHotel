package com.example.bookinghotel.enity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Room  {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double price;
    private String description;
    private String imageUrl;
    private int stock;

    @ManyToOne(fetch = FetchType.EAGER)
    private Size size;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Room room = (Room) o;
        return false;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
