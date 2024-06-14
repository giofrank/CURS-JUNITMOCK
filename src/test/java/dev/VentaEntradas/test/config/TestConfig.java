package dev.VentaEntradas.test.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
@TestConfiguration
public class TestConfig {

    private Integer variableGlobal = 0; 
    
    public Integer getVariableGlobal() {
        return variableGlobal;
    }

    public void setVariableGlobal(Integer nuevoValor) {
        this.variableGlobal = nuevoValor;
    }
}
