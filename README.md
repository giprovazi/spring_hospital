# 🏥 Backend de Clínica Médica – Java & Spring Boot

Projeto backend desenvolvido em **Java com Spring Boot**, simulando o sistema de uma clínica médica. O foco é demonstrar **boas práticas de backend**, modelagem correta de entidades, relacionamento entre tabelas, regras de negócio e uso profissional do **Spring Data JPA**.

Este projeto foi pensado como **portfólio**, voltado para recrutadores e empresas que buscam desenvolvedores Java backend.

---

## 🚀 Tecnologias Utilizadas

* **Java 17+**
* **Spring Boot**
* **Spring Data JPA / Hibernate**
* **REST API**
* **Postman** (testes de requisições)
* **Banco de Dados Relacional** (PostgreSQL)
* **Maven**

---

## 📌 Funcionalidades Implementadas

### 👨‍⚕️ Médicos

* Cadastro de médicos
* Ativação e inativação (soft delete)
* Listagem apenas de médicos ativos

### 🧑‍🤝‍🧑 Pacientes

* Cadastro de pacientes
* Associação com consultas
* Validação de dados (CPF, data de nascimento)

### 📅 Consultas

* Agendamento de consultas
* Relacionamento **ManyToOne** com médico e paciente
* Controle de data e horário com `LocalDateTime`
* Status da consulta usando `enum`

---

## 🗂️ Modelagem de Dados (JPA)

* Uso correto de **@Entity** e **@Table**
* Chaves primárias com `@Id` e `@GeneratedValue(strategy = GenerationType.IDENTITY)`
* Relacionamentos:

  * `@ManyToOne` (Consulta → Médico / Paciente)
  * `@OneToMany` (Paciente → Consultas)
* Uso de **wrappers (`Long`, `Integer`)** para compatibilidade com JPA
* Mapeamento explícito com `@JoinColumn`

---

## ⚙️ Regras de Negócio

* Um médico **não pode ter duas consultas no mesmo horário** (regra preparada para validação no service)
* Consultas sempre devem estar vinculadas a um paciente
* Status de consulta controlado por enum (ex: AGENDADA, CANCELADA, FINALIZADA)

---

## 🌐 API REST

A aplicação expõe endpoints REST que podem ser consumidos via **Postman** ou qualquer frontend.

Exemplos:

* `POST /medicos`
* `GET /medicos/ativos`
* `POST /pacientes`
* `POST /consultas`

> Não é necessário criar uma `main` manual para salvar dados — o Spring Boot inicializa a aplicação automaticamente.

---

## 🧠 Conceitos Demonstrados

* Diferença entre **tipos primitivos e wrappers**
* Streams e Lambda Expressions (`filter`, `map`, `toList`)
* Method Reference (`Classe::metodo`)
* Lazy Loading (`FetchType.LAZY`)
* Boas práticas de separação em **Controller, Service e Repository**

---

## 🎯 Objetivo do Projeto

Este projeto tem como objetivo demonstrar:

* Base sólida em **Java Backend**
* Conhecimento prático em **Spring Boot e JPA**
* Capacidade de modelar sistemas reais
* Escrita de código limpo, organizado e escalável

---

## 👤 Autor

**Giovanni Romano Provazi**
Estudante de Engenharia de Software – FIAP
Foco em Backend Java e desenvolvimento de APIs REST

---

📌 *Projeto em evolução, novas validações e funcionalidades serão adicionadas conforme o aprendizado avança.*
