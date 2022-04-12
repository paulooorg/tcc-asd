package io.github.paulooorg.middlewaresge.integration.dto.response;

import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustoSeguroDTO {

    private BigDecimal custoTotal;
}
