package com.ifma.frequencia.runner;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ifma.frequencia.domain.enumerate.TipoMicro;
import com.ifma.frequencia.domain.model.Aluno;
import com.ifma.frequencia.domain.model.Curso;
import com.ifma.frequencia.domain.model.Estagio;
import com.ifma.frequencia.domain.model.HorasEstagio;
import com.ifma.frequencia.domain.model.Micro;
import com.ifma.frequencia.domain.model.Sala;
import com.ifma.frequencia.domain.repository.AlunoRepository;
import com.ifma.frequencia.domain.repository.CursoRepository;
import com.ifma.frequencia.domain.repository.EstagioRespository;
import com.ifma.frequencia.domain.repository.HorasEstagioRepository;
import com.ifma.frequencia.domain.repository.MicroRepository;
import com.ifma.frequencia.domain.repository.SalaRepository;
import com.ifma.frequencia.domain.service.MicroService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class Run implements CommandLineRunner {

    private final Boolean run = true;

    private final MicroService microService;

    private final AlunoRepository alunoRepository;
    private final EstagioRespository estagioRespository;
    private final HorasEstagioRepository horasEstagioRepository;
    private final SalaRepository salaRepository;
    private final MicroRepository microRepository;
    private final CursoRepository cursoRepository;

    @Override
    public void run(String... args) throws Exception {

        
        // ===================================== 
        if(!run){
            return;
        }
        
        // ===================================== 
        // SALAS
        Sala sala1 = new Sala();
        sala1.setDescricao("S01P03");
        
        salaRepository.save(sala1);
        
        // ===================================== 
        // MICROCONTROLADORES
        Micro micro1 = new Micro();
        micro1.setLocalizacao(sala1);

        Micro micro2 = new Micro();
        micro2.setLocalizacao(sala1);
        micro2.setTipoMicro(TipoMicro.ARDUINO);
        
        microRepository.save(micro1);
        microRepository.save(micro2);

        // ===================================== 
        // CURSOS
        Curso curso1 = new Curso();
        curso1.setNome("Técnico em Informática");

        Curso curso2 = new Curso();
        curso2.setNome("Técnico em Química");

        Curso curso3 = new Curso();
        curso3.setNome("Graduação em Sistemas de Informação");

        cursoRepository.save(curso1);
        cursoRepository.save(curso2);
        cursoRepository.save(curso3);

        // ===================================== 
        // ALUNOS
        Aluno aluno1 = new Aluno();
        aluno1.setNome("David Gomesh");
        aluno1.setCartao("123");
        aluno1.setCurso(curso3);
        
        Aluno aluno2 = new Aluno();
        aluno2.setNome("Ana Beatriz");
        aluno2.setCartao("ASD");
        aluno2.setCurso(curso1);
        
        Aluno aluno3 = new Aluno();
        aluno3.setNome("Marcos Oliveira");
        aluno3.setCartao("321");
        aluno3.setCurso(curso2);

        alunoRepository.save(aluno1);
        alunoRepository.save(aluno2);
        alunoRepository.save(aluno3);

        // ===================================== 
        // ALUNOS
        Estagio estagio = new Estagio();
        estagio.setAtivo(true);
        estagio.setAluno(aluno1);
        estagioRespository.save(estagio);

        HorasEstagio horasEstagio = new HorasEstagio();
        horasEstagio.setEstagio(estagio);
        horasEstagio.setDataRegistro(LocalDate.now());
        horasEstagio.setHoraInicio(LocalTime.now().minusHours(2));
        horasEstagioRepository.save(horasEstagio);

        HorasEstagio horasEstagio1 = new HorasEstagio();
        horasEstagio1.setEstagio(estagio);
        horasEstagio1.setDataRegistro(LocalDate.now().plusDays(1));
        horasEstagio1.setHoraInicio(LocalTime.now());
        horasEstagio1.setHoraFim(LocalTime.now().plusHours(4).plusMinutes(21).plusSeconds(32));
        horasEstagioRepository.save(horasEstagio1);

        HorasEstagio horasEstagio2 = new HorasEstagio();
        horasEstagio2.setEstagio(estagio);
        horasEstagio2.setDataRegistro(LocalDate.now().plusDays(2));
        horasEstagio2.setHoraInicio(LocalTime.now());
        horasEstagio2.setHoraFim(LocalTime.now().plusHours(1).plusMinutes(00).plusSeconds(1));
        horasEstagioRepository.save(horasEstagio2);

        // ===================================== 
        // LOGS
        microService.leitura(micro1, "321");
        microService.leitura(micro2, "987");
        microService.leitura(micro1, "123");
        microService.leitura(micro1, "123312");
        microService.leitura(micro1, "123");
        microService.leitura(micro2, "123");
        microService.leitura(micro2, "987");
        microService.leitura(micro1, "asdas");
        microService.leitura(micro2, "asdadd");
        microService.leitura(micro2, "987");
    }
    
}
