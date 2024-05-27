package edu.badpals.ExamenAccesoADatos.domain;


import jakarta.persistence.*;

@Entity
@Table(name="t_orders")
public class Order {

    public Order(){}

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ord_id", unique = true)
    String id = "";


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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

