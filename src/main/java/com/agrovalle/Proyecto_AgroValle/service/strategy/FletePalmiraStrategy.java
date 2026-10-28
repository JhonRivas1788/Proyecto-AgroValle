package com.agrovalle.Proyecto_AgroValle.service.strategy;

public class FletePalmiraStrategy implements FleteStrategy {
    @Override
    public double calcularFlete(double pesoKg) {
        return pesoKg * 1200.0; // Tarifa específica para Palmira
    }
}