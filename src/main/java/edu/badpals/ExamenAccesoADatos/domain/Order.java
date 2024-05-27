package edu.badpals.ExamenAccesoADatos.domain;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name="t_orders")
public class Order extends PanacheEntityBase {

    public Order(){}

    public Order(Wizard wizard, MagicalItem item){
        this.wizard = wizard;
        this.item = item;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ord_id", unique = true)
    Long id;


    @ManyToOne
    @JoinColumn(name="ord_wizard")
    Wizard wizard;


    @OneToOne
    @JoinColumn(name="ord_item")
    MagicalItem item;



    @Override
    public String toString() {
        return wizard + ", " + item;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Wizard getWizard() {
        return wizard;
    }

    public void setWizard(Wizard wizard) {
        this.wizard = wizard;
    }

    public MagicalItem getItem() {
        return item;
    }

    public void setItem(MagicalItem item) {
        this.item = item;
    }
}

