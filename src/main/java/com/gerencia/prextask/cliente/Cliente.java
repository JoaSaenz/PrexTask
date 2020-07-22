package com.gerencia.prextask.cliente;

public class Cliente {
    private final String clienteId;
    private final String razonSocial;

    public Cliente(String clienteId, String razonSocial) {
        this.clienteId = clienteId;
        this.razonSocial = razonSocial;
    }

    public String getClienteId() {
        return clienteId;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    @Override
    public String toString() {
        return "Cliente [clienteId=" + clienteId + ", razonSocial=" + razonSocial + "]";
    }

    

}