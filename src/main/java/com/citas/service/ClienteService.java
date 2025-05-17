package com.citas.service;

import com.citas.dto.ClienteDTO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;


@Service
public class ClienteService {

    private final RestTemplate restTemplate;

    @Value("${servicio.usuarios.url}")
    private String usuariosUrl;

    public ClienteService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String obtenerCorreoCliente(Long idCliente) {
        try {
            String url = usuariosUrl + "/clientes/" + idCliente;
            ClienteDTO cliente = restTemplate.getForObject(url, ClienteDTO.class);
            return cliente != null ? cliente.getCorreo() : null;
        } catch (Exception e) {
            return null;
        }
    }

    public ClienteDTO ListarxCodigo(Long idCliente) {
        try {
            String url = usuariosUrl + "/clientes/" + idCliente;
            ClienteDTO cliente = restTemplate.getForObject(url, ClienteDTO.class);
            return cliente;
        } catch (Exception e) {
            return null;
        }
    }
}

