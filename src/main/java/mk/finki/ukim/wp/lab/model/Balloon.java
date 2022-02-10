package mk.finki.ukim.wp.lab.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "balloons")
public class Balloon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    private Manufacturer manufacturer;

    public Balloon(String name, String description,Manufacturer manufacturer) {
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
    }

    public Balloon(Long id, String name, String description,Manufacturer manufacturer) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
    }

    public Balloon() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Balloon)) return false;

        Balloon balloon = (Balloon) o;

        return getName() != null ? getName().equals(balloon.getName()) : balloon.getName() == null;
    }

}
