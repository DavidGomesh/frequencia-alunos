package com.ifma.frequencia.domain.model;

import java.time.Duration;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.ifma.frequencia.domain.utils.Duracao;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Estagio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstagio;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "fk_aluno")
    private Aluno aluno;

    @NotNull
    private Boolean ativo = true;

    @OneToMany(mappedBy = "estagio")
    private Set<HorasEstagio> horasEstagio;

    public Duracao horasTotais(){
        return new Duracao(
            Duration.ofMillis(horasEstagio.stream()
                .filter(horas -> horas.getHoraFim() != null)
                .mapToLong(
                    horas -> horas.horasTotais().toMillis()
                ).sum()
            )
        );
    }
}
