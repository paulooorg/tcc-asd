package io.github.paulooorg.entrega.service;

import io.github.paulooorg.entrega.controller.dto.request.FormFalhaEntregaDTO;
import io.github.paulooorg.entrega.controller.dto.request.FormSucessoEntregaDTO;
import io.github.paulooorg.entrega.controller.dto.response.EntregaDTO;
import io.github.paulooorg.entrega.entity.Assinatura;
import io.github.paulooorg.entrega.entity.Entrega;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface EntregaService {
    Entrega criarEntregaComoEntregue(FormSucessoEntregaDTO formSucessoEntrega);

    Entrega criarEntregaComoNaoEntregue(FormFalhaEntregaDTO formFalhaEntrega);

    EntregaDTO buscarEntregaPorCodigoDoPedido(String codigo);

    Resource buscarAssinaturaDaEntrega(Long entregaId);

    Assinatura criarAssinatura(MultipartFile arquivo);
}
