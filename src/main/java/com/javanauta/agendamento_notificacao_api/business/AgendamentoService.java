package com.javanauta.agendamento_notificacao_api.business;

import com.javanauta.agendamento_notificacao_api.exception.NotFoundException;
import com.javanauta.agendamento_notificacao_api.infrastructure.entities.Agendamento;
import com.javanauta.agendamento_notificacao_api.mapper.IAgendamentoMapper;
import com.javanauta.agendamento_notificacao_api.dto.in.AgendamentoRecord;
import com.javanauta.agendamento_notificacao_api.dto.out.AgendamentoRecordOut;
import com.javanauta.agendamento_notificacao_api.infrastructure.repositories.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class AgendamentoService {

    private final AgendamentoRepository repository;

    private final IAgendamentoMapper agendamentoMapper;


    public AgendamentoRecordOut gravarAgendamento(AgendamentoRecord agendamento) {
        return agendamentoMapper.paraOut(repository.save(agendamentoMapper.paraEntity(agendamento)));
    }

    public AgendamentoRecordOut buscarAgendamentosPorId(Long id) {
        return agendamentoMapper.paraOut(repository.findById(id)
                .orElseThrow(()-> new NotFoundException("ID não encontrado")));
    }

    public void cancelarAgendamento(Long id) {
        Agendamento agendamento = repository.findById(id)
                .orElseThrow(()-> new NotFoundException("ID não encontrado"));
        repository.save(agendamentoMapper.paraEntityCancelamento(agendamento));
    }
}
