package com.javanauta.agendamento_notificacao_api.mapper;

import com.javanauta.agendamento_notificacao_api.dto.in.AgendamentoRecord;
import com.javanauta.agendamento_notificacao_api.dto.out.AgendamentoRecordOut;
import com.javanauta.agendamento_notificacao_api.infrastructure.entities.Agendamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAgendamentoMapper {
    Agendamento paraEntity(AgendamentoRecord agendamento);
    AgendamentoRecordOut paraOut(Agendamento agendamento);
}

