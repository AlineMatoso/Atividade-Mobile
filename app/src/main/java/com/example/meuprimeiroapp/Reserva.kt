package com.example.meuprimeiroapp

data class Reserva(
    val id: Int = 0,
    val nome: String,
    val data: String,
    val local: String, // Vamos usar 'local' para armazenar a Sala Escolhida
    val descricao: String,
    val favorito: Boolean = false // Podemos usar no futuro para favoritar reservas frequentes
)