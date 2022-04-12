package io.github.paulooorg.cliente.service;

import io.github.paulooorg.cliente.controller.dto.request.FormCalcularFreteDTO;
import io.github.paulooorg.cliente.controller.dto.request.FormCriarPedidoDTO;
import io.github.paulooorg.cliente.controller.dto.response.CustoFreteDTO;
import io.github.paulooorg.cliente.controller.dto.response.EnderecoDTO;
import java.util.List;

public interface ClienteService {
    CustoFreteDTO calcularFrete(Long clienteId, FormCalcularFreteDTO formCalcularFrete);

    void criarPedido(Long clienteId, FormCriarPedidoDTO formCriarPedido);

    List<EnderecoDTO> buscarEnderecosDoCliente(Long clienteId);
}
