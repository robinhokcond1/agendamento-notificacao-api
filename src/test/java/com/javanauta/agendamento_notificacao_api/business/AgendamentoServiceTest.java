package com.javanauta.agendamento_notificacao_api.business;

import com.javanauta.agendamento_notificacao_api.dto.in.AgendamentoRecord;
import com.javanauta.agendamento_notificacao_api.dto.out.AgendamentoRecordOut;
import com.javanauta.agendamento_notificacao_api.exception.NotFoundException;
import com.javanauta.agendamento_notificacao_api.infrastructure.entities.Agendamento;
import com.javanauta.agendamento_notificacao_api.infrastructure.enums.StatusNotificacaoEnum;
import com.javanauta.agendamento_notificacao_api.infrastructure.repositories.AgendamentoRepository;
import com.javanauta.agendamento_notificacao_api.mapper.IAgendamentoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class AgendamentoServiceTest {

    @InjectMocks
    private AgendamentoService agendamentoService;

    @Mock
    private AgendamentoRepository repository;

    @Mock
    private IAgendamentoMapper agendamentoMapper;

    private Agendamento agendamento;
    private AgendamentoRecord agendamentoRecord;
    private AgendamentoRecordOut agendamentoRecordOut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        agendamentoRecord = new AgendamentoRecord(
                "teste@email.com",
                "+553499999999",
                "Mensagem de teste",
                LocalDateTime.of(2025, 2, 10, 12, 30)
        );

        agendamento = new Agendamento(
                1L,
                "teste@email.com",
                "+553499999999",
                LocalDateTime.of(2025, 2, 10, 12, 30),
                LocalDateTime.now(),
                null,
                "Mensagem de teste",
                StatusNotificacaoEnum.AGENDADO
        );

        agendamentoRecordOut = new AgendamentoRecordOut(
                1L,
                agendamento.getEmailDestinatario(),
                agendamento.getTelefoneDestinatario(),
                agendamento.getMensagem(),
                agendamento.getDataHoraEnvio(),
                agendamento.getStatusNotificacao()
        );
    }

    @Test
    void deveGravarAgendamentoComSucesso() {
        when(agendamentoMapper.paraEntity(any(AgendamentoRecord.class))).thenReturn(agendamento);
        when(repository.save(any(Agendamento.class))).thenReturn(agendamento);
        when(agendamentoMapper.paraOut(any(Agendamento.class))).thenReturn(agendamentoRecordOut);

        AgendamentoRecordOut resultado = agendamentoService.gravarAgendamento(agendamentoRecord);

        assertNotNull(resultado);
        assertEquals(agendamentoRecord.emailDestinatario(), resultado.emailDestinatario());
        verify(repository, times(1)).save(any(Agendamento.class));
    }

    @Test
    void deveBuscarAgendamentoPorIdComSucesso() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(agendamento));
        when(agendamentoMapper.paraOut(any(Agendamento.class))).thenReturn(agendamentoRecordOut);

        AgendamentoRecordOut resultado = agendamentoService.buscarAgendamentosPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.id());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void deveLancarNotFoundExceptionQuandoBuscarPorIdInexistente() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> agendamentoService.buscarAgendamentosPorId(999L));
        verify(repository, times(1)).findById(999L);
    }

    @Test
    void deveCancelarAgendamentoComSucesso() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(agendamento));
        when(agendamentoMapper.paraEntityCancelamento(any(Agendamento.class))).thenReturn(agendamento);
        when(repository.save(any(Agendamento.class))).thenReturn(agendamento);

        agendamentoService.cancelarAgendamento(1L);

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(any(Agendamento.class));
    }

    @Test
    void deveLancarNotFoundExceptionAoCancelarAgendamentoInexistente() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> agendamentoService.cancelarAgendamento(999L));
        verify(repository, times(1)).findById(999L);
    }
}
