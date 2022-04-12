package io.github.paulooorg.entrega.entity;

import ch.qos.logback.core.pattern.util.AsIsEscapeUtil;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Assinatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] arquivo;

    public Assinatura(byte[] arquivo) {
        this.arquivo = arquivo;
    }
}
