package com.example.pmdm_practica3_equipoesports.data

import androidx.compose.runtime.mutableStateListOf

data class Jugador(
    val id: Int,
    val nombre: String,
    val nickname: String,
    val rol: String
)

object JugadoresEjemplo {
    val jugadores = mutableStateListOf(
        Jugador(id = 1, nombre = "Alexia Putellas", nickname = "La Reina", rol = "Centrocampista"),
        Jugador(id = 2, nombre = "Aitana Bonmatí", nickname = "Aiti", rol = "Centrocampista"),
        Jugador(id = 3, nombre = "Salma Paralluelo", nickname = "La Bala", rol = "Delantera"),
        Jugador(id = 4, nombre = "Irene Paredes", nickname = "El Muro", rol = "Defensa"),
        Jugador(id = 5, nombre = "Cata Coll", nickname = "Cata", rol = "Portera")
    )
}