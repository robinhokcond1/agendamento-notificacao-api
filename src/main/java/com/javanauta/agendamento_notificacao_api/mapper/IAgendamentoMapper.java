package com.javanauta.agendamento_notificacao_api.mapper;

import com.javanauta.agendamento_notificacao_api.dto.in.AgendamentoRecord;
import com.javanauta.agendamento_notificacao_api.dto.out.AgendamentoRecordOut;
import com.javanauta.agendamento_notificacao_api.infrastructure.entities.Agendamento;
import com.javanauta.agendamento_notificacao_api.infrastructure.enums.StatusNotificacaoEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface IAgendamentoMapper {

    Agendamento paraEntity(AgendamentoRecord agendamento);

    AgendamentoRecordOut paraOut(Agendamento agendamento);

    @Mapping(target = "dataHoraAgendamento", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "statusNotificacao", expression = "java(com.javanauta.agendamento_notificacao_api.infrastructure.enums.StatusNotificacaoEnum.CANCELADO)")
    Agendamento paraEntityCancelamento(Agendamento agendamento);
}
