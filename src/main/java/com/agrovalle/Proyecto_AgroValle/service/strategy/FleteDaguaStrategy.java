package com.agrovalle.Proyecto_AgroValle.service.strategy;

public class FleteDaguaStrategy implements FleteStrategy {
    @Override
    public double calcularFlete(double pesoKg) {
        return pesoKg * 1500.0; // Tarifa específica para Dagua
    }
}