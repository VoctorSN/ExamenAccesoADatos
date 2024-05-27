package edu.badpals.ExamenAccesoADatos.domain;


import jakarta.persistence.*;


@Entity
@Table(name="t_wizards")
public class Wizard {

    public Wizard(){}


    @Id
    @Column(name = "wizard_name", unique = true)
    String name = "";


    @Column(name = "wizard_dexterity")
    int dexterity = 0;


    @Column(name = "wizard_person")
    @Enumerated(EnumType.STRING)
    Person person;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return name + ", " + dexterity + ", " + person;
    }
}
