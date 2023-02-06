package com.ifma.frequencia.domain.model;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.transaction.Transactional;
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

    @OneToMany(mappedBy = "estagio", fetch = FetchType.EAGER)
    private List<HorasEstagio> horasEstagio;

    @Transactional
    public Duracao horasTotais(){
        return new Duracao(
            Duration.ofMillis(horasEstagio.stream()
                .filter(horas -> horas.getHoraFim() != null)
                .mapToLong(horas -> horas.horasTotais().getDuration().toMillis())
                .sum()
            )
        );
    }
}
