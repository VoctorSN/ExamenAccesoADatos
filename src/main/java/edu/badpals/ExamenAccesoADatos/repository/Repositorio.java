package edu.badpals.ExamenAccesoADatos.repository;


import edu.badpals.ExamenAccesoADatos.domain.MagicalItem;
import edu.badpals.ExamenAccesoADatos.domain.Wizard;
import jakarta.enterprise.context.ApplicationScoped;

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
}
