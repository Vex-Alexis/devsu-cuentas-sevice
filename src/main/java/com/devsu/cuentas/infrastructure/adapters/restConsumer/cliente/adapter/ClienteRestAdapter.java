package com.devsu.cuentas.infrastructure.adapters.restConsumer.cliente.adapter;

import com.devsu.cuentas.domain.model.cliente.Cliente;
import com.devsu.cuentas.domain.model.cliente.gateway.ClienteRestGateway;
import com.devsu.cuentas.domain.model.exceptions.NotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class ClienteRestAdapter implements ClienteRestGateway {

    private static final String CLIENTES_URL_TEMPLATE = "http://localhost:8080/clientes/identificacion/";

    private final RestTemplate restTemplate;

    public ClienteRestAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Cliente obtenerClientePorIdentificacion(String identificacion) {
        String url = CLIENTES_URL_TEMPLATE + identificacion;
        try {
            return restTemplate.getForObject(url, Cliente.class);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new NotFoundException("No se encontró un cliente con identificación " + identificacion);
        } catch (ResourceAccessException ex) {
            throw new RuntimeException("No se pudo conectar al servicio de clientes", ex);
        }
    }
}
