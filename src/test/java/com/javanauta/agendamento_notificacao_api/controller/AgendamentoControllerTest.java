package com.javanauta.agendamento_notificacao_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javanauta.agendamento_notificacao_api.business.AgendamentoService;
import com.javanauta.agendamento_notificacao_api.dto.in.AgendamentoRecord;
import com.javanauta.agendamento_notificacao_api.dto.out.AgendamentoRecordOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AgendamentoController.class)
class AgendamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AgendamentoService agendamentoService;

    private AgendamentoRecord agendamentoRecord;
    private AgendamentoRecordOut agendamentoRecordOut;

    @BeforeEach
    void setUp() {
        agendamentoRecord = new AgendamentoRecord(
                "teste@email.com",
                "+553499999999",
                "Mensagem de teste",
                LocalDateTime.of(2025, 2, 10, 12, 30)
        );

        agendamentoRecordOut = new AgendamentoRecordOut(
                1L,
                agendamentoRecord.emailDestinatario(),
                agendamentoRecord.telefoneDestinatario(),
                agendamentoRecord.mensagem(),
                agendamentoRecord.dataHoraEnvio(),
                null
        );
    }

    @Test
    void deveGravarAgendamentoComSucesso() throws Exception {
        Mockito.when(agendamentoService.gravarAgendamento(any(AgendamentoRecord.class)))
                .thenReturn(agendamentoRecordOut);

        mockMvc.perform(post("/agendamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(agendamentoRecord)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.emailDestinatario").value(agendamentoRecord.emailDestinatario()));
    }

    @Test
    void deveBuscarAgendamentoPorIdComSucesso() throws Exception {
        Mockito.when(agendamentoService.buscarAgendamentosPorId(anyLong()))
                .thenReturn(agendamentoRecordOut);

        mockMvc.perform(get("/agendamento/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.emailDestinatario").value(agendamentoRecord.emailDestinatario()));
    }

    @Test
    void deveCancelarAgendamentoComSucesso() throws Exception {
        Mockito.doNothing().when(agendamentoService).cancelarAgendamento(anyLong());

        mockMvc.perform(put("/agendamento/1/cancelar"))
                .andExpect(status().isNoContent());
    }
}
