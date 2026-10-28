package com.agrovalle.Proyecto_AgroValle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.agrovalle.Proyecto_AgroValle.service.CalculadorFleteService;
import com.agrovalle.Proyecto_AgroValle.service.strategy.FleteDaguaStrategy;
import com.agrovalle.Proyecto_AgroValle.service.strategy.FletePalmiraStrategy;

class CalculadorFleteServiceTest {

    private final CalculadorFleteService calculadorService = new CalculadorFleteService();

    @Test
    void testCalcularFleteDagua() {
        FleteDaguaStrategy estrategia = new FleteDaguaStrategy();
        double resultado = calculadorService.procesarFlete(estrategia, 10.0);
        assertEquals(15000.0, resultado, 0.01);
    }

    @Test
    void testCalcularFletePalmira() {
        FletePalmiraStrategy estrategia = new FletePalmiraStrategy();
        double resultado = calculadorService.procesarFlete(estrategia, 10.0);
        assertEquals(12000.0, resultado, 0.01);
    }
}