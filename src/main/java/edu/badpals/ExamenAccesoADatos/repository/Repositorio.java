package edu.badpals.ExamenAccesoADatos.repository;


import edu.badpals.ExamenAccesoADatos.domain.MagicalItem;
import edu.badpals.ExamenAccesoADatos.domain.Order;
import edu.badpals.ExamenAccesoADatos.domain.Person;
import edu.badpals.ExamenAccesoADatos.domain.Wizard;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class Repositorio {

    public Optional<Wizard> loadWizard(String name) {
        return Wizard.findByIdOptional(name);
    }

    public Optional<MagicalItem> loadItem(String name) {
        List<MagicalItem> items = MagicalItem.listAll();
        return items.stream().filter(item -> item.getName().equals(name)).findFirst();
    }

    public Optional<MagicalItem> loadItem(MagicalItem item) {
        List<MagicalItem> items = MagicalItem.listAll();
        return items.stream().filter(i -> i.getName().equals(item.getName()) && item.getQuality() == i.getQuality() && item.getType().equals(i.getType())).findFirst();
    }

    public List<MagicalItem> loadItems(String name) {
        List<MagicalItem> items = MagicalItem.listAll();
        return items.stream().filter(item -> item.getName().equals(name)).toList();
    }

    public Optional<Order> loadOrder(String wizard,String item){

        List<Order> orders = Order.listAll();
        return orders.stream().filter(o -> o.getWizard().getName().equals(wizard) && o.getItem().getName().equals(item)).findFirst();
    }


    @Transactional
    public Optional<Order> placeOrder(String wizardName, String itemName) {
        Order orden = null;
        Optional<Wizard> wizard = this.loadWizard(wizardName);
        Optional<MagicalItem> item = this.loadItem(itemName);
        if (wizard.isPresent() && wizard.get().getPerson() != Person.MUDBLOOD && item.isPresent()){
            orden = new Order(wizard.get(),item.get());
            orden.persist();
        }
        return this.loadOrder(wizardName,itemName);
    }

    @Transactional
    public void createItem(String name, int quality, String type) {
        if (!name.isEmpty() && !type.isEmpty()){
            MagicalItem item = new MagicalItem(name,quality,type);
            item.persist();
        }
    }

    @Transactional
    public void createItems(List<MagicalItem> items) {
        for (MagicalItem item:items){
            this.createItem(item.getName(),item.getQuality(),item.getType());
        }
    }

    @Transactional
    public void deleteItem(MagicalItem item) {
        Optional<MagicalItem> itemBBDD = this.loadItem(item);
        itemBBDD.ifPresent(magicalItem -> MagicalItem.deleteById(magicalItem.getId()));
    }

    public List<MagicalItem> items (){
        return MagicalItem.listAll();
    }
}
