package com.agrovalle.Proyecto_AgroValle.service;

import org.springframework.stereotype.Service;

import com.agrovalle.Proyecto_AgroValle.service.strategy.FleteStrategy;

@Service
public class CalculadorFleteService {

    public double procesarFlete(FleteStrategy estrategia, double peso) {
        return estrategia.calcularFlete(peso);
    }
}