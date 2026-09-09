package com.projects.chamados.utils;

public class Constants {
    public static final String TECHNICIAN_NOT_FOUND = "Técnico não encontrado.";
    public static final String EQUIPMENT_NOT_FOUND = "Equipamento não encontrado.";
    public static final String OPEN_TICKET_NOT_FOUND = "Chamado não encontrado.";
    public static final String ID_CHAMADO_ALREADY_EXISTS = "Já existe um chamado com o ID cadastrado.";
    public static final String CHECK_THE_END_DATE_AND_END_TIME_FIELDS = "Verifique os campos de Data fim e Hora fim.";
    public static final String INVALID_CREDENTIALS = "Credenciais inválidas.";
    public final static String[] PUBLIC_ENDPOINTS = {"/auth/login", "/h2-console/**", "/swagger-ui/**", "/v3/api-docs/**"};
}
