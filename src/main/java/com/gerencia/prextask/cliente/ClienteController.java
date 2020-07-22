package com.gerencia.prextask.cliente;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {
    
    private static final List<Cliente> CLIENTES = Arrays.asList(
        new Cliente("111111111", "EMPRESA 1"),
        new Cliente("222222222", "EMPRESA 2")
    );

    //hasRole('ROLE_') hasAnyRole('ROLE_') hasAuthority('premission') hasAnyAuthority('permission')

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public List<Cliente> getAllClientes(){
        return CLIENTES;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('cliente:write')")
    public void registerNewCliente(@RequestBody Cliente cliente){
        System.out.println(cliente);
    }

    @DeleteMapping(path = "{clienteId}")
    @PreAuthorize("hasAuthority('cliente:write')")
    public void deleteCliente(@PathVariable("clienteId") String clienteId){
        System.out.println(clienteId);
    }

    @PutMapping(path = "{clienteId}")
    @PreAuthorize("hasAuthority('cliente:write')")
    public void updateCliente(@PathVariable("clienteId") String clienteId, @RequestBody Cliente cliente){
        System.out.println(String.format("%s %s", clienteId, cliente));
    }


}