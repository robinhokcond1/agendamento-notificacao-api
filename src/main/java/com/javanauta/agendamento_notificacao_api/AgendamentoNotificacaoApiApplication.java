package com.javanauta.agendamento_notificacao_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.javanauta.agendamento_notificacao_api")
public class AgendamentoNotificacaoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendamentoNotificacaoApiApplication.class, args);
	}

}
