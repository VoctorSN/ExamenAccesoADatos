package edu.badpals.ExamenAccesoADatos;

import edu.badpals.ExamenAccesoADatos.repository.Repositorio;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ServiceItem {

    @Inject
    Repositorio repo;
    public ServiceItem(){}
}
