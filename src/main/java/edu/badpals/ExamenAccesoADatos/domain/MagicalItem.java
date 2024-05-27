package edu.badpals.ExamenAccesoADatos.domain;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name="t_items")
public class MagicalItem extends PanacheEntityBase {



    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "item_id")
    Long id;


    @Column(name = "item_name")
    String name = "";


    @Column(name = "item_quality")
    int quality = 0;


    @Column(name = "item_type")
    String type = "";

    public MagicalItem(){}

    public MagicalItem(String name, int quality, String type) {
        this.name = name;
        this.quality = quality;
        this.type = type;
    }

    @Override
    public String toString() {
        return name + ", " + quality + ", " + type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
