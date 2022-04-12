package io.github.paulooorg.frete.service.impl;

import io.github.paulooorg.frete.controller.dto.request.FormCalcularFreteDTO;
import io.github.paulooorg.frete.controller.dto.request.FormServicoOpcionalDTO;
import io.github.paulooorg.frete.controller.dto.response.CustoFreteDTO;
import io.github.paulooorg.frete.entity.TipoServicoOpcional;
import io.github.paulooorg.frete.integration.MiddlewareSGEClient;
import io.github.paulooorg.frete.integration.dto.CustoSeguroDTO;
import io.github.paulooorg.frete.integration.dto.FormCalcularValorSeguroDTO;
import io.github.paulooorg.frete.service.FreteService;
import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FreteServiceImpl implements FreteService {

    private final MiddlewareSGEClient middlewareSGEClient;

    private static final Integer PRAZO_DIAS_UTEIS_FIXO = 10;

    private static final BigDecimal VALOR_POR_VOLUME = new BigDecimal("2.89");

    @Autowired
    public FreteServiceImpl(MiddlewareSGEClient middlewareSGEClient) {
        this.middlewareSGEClient = middlewareSGEClient;
    }

    @Override
    public CustoFreteDTO calcular(FormCalcularFreteDTO formCalcularFrete) {
        CustoFreteDTO custoFrete = new CustoFreteDTO();
        custoFrete.setPrazoDiasUteis(PRAZO_DIAS_UTEIS_FIXO);
        adicionarCustoFrete(custoFrete, formCalcularFrete);
        adicionarCustoServicos(custoFrete, formCalcularFrete);
        custoFrete.setCustoTotal(custoFrete.getCustoFrete().add(custoFrete.getCustoServicos()));
        return custoFrete;
    }

    private void adicionarCustoFrete(CustoFreteDTO custoFrete, FormCalcularFreteDTO formCalcularFrete) {
        Integer quantidadeVolumes = formCalcularFrete.getVolumes().size();
        BigDecimal total = VALOR_POR_VOLUME.multiply(new BigDecimal(quantidadeVolumes));
        custoFrete.setCustoFrete(total);
    }

    private void adicionarCustoServicos(CustoFreteDTO custoFrete, FormCalcularFreteDTO formCalcularFrete) {
        adicionarCustoSeguro(custoFrete, formCalcularFrete);
    }

    private void adicionarCustoSeguro(CustoFreteDTO custoFrete, FormCalcularFreteDTO formCalcularFrete) {
        Optional<FormServicoOpcionalDTO> dadosCalcularSeguro = getDadosParaCalcularSeguro(formCalcularFrete);
        if (dadosCalcularSeguro.isPresent()) {
            BigDecimal valorDeclarado = new BigDecimal(
                dadosCalcularSeguro.get().getParametros().get("valorDeclarado").toString()
            );
            FormCalcularValorSeguroDTO formCalcularValorSeguro = FormCalcularValorSeguroDTO
                .builder()
                .cepOrigem(formCalcularFrete.getCepOrigem())
                .cepDestino(formCalcularFrete.getCepDestino())
                .valorDeclarado(valorDeclarado)
                .build();
            CustoSeguroDTO custoSeguro = middlewareSGEClient.calcularSeguro(formCalcularValorSeguro);
            custoFrete.setCustoServicos(
                Optional
                    .ofNullable(custoFrete.getCustoServicos())
                    .orElse(BigDecimal.ZERO)
                    .add(custoSeguro.getCustoTotal())
            );
        }
    }

    private Optional<FormServicoOpcionalDTO> getDadosParaCalcularSeguro(FormCalcularFreteDTO formCalcularFrete) {
        return formCalcularFrete
            .getServicosOpcionais()
            .stream()
            .filter(so -> TipoServicoOpcional.SEGURO.equals(so.getTipo()))
            .findFirst();
    }
}
