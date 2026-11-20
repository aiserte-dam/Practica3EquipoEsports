package com.example.pmdm_practica3_equipoesports.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pmdm_practica3_equipoesports.data.Jugador
import com.example.pmdm_practica3_equipoesports.data.JugadoresEjemplo
import com.example.pmdm_practica3_equipoesports.ui.theme.PMDMPractica3EquipoEsportsTheme


@Composable
fun PantallaEquipo() {
    // Lista observable: al añadir/borrar/editar, la UI se recompone sola
    val jugadores = JugadoresEjemplo.jugadores

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFF2F8))
            .statusBarsPadding()
    ) {
        Text(
            text = "TEAM TITANS",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 24.dp, bottom = 16.dp)
        )

        // weight(1f): la lista ocupa el espacio restante (y deja sitio
        // al formulario que añadiremos en el Bloque 2)
        ListaJugadores(
            jugadores = jugadores,
            modifier = Modifier.weight(1f)
        )
    }
}

// ---------- LISTA (Actividad 3) ----------
@Composable
fun ListaJugadores(
    jugadores: List<Jugador>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        // key = id → cada fila tiene identidad estable al borrar/editar
        items(jugadores, key = { it.id }) { jugador ->
            JugadorItem(jugador = jugador)
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 32.dp, vertical = 4.dp),
                thickness = 1.dp,
                color = Color(0xFFB0B8C8)
            )
        }
    }
}

// ---------- FILA DE UN JUGADOR ----------
@Composable
fun JugadorItem(jugador: Jugador, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar circular con la inicial: mantiene el estilo de la práctica 3
        // no necesita una foto por jugadora
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = jugador.nombre.take(1),
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(
                text = jugador.nickname,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1B1F3B)
            )
            Text(text = jugador.nombre, fontSize = 15.sp, color = Color.DarkGray)
            Text(text = jugador.rol, fontSize = 14.sp, color = MaterialTheme.colorScheme.primary)
        }
    }
}

// ---------- PREVIEW ----------
@Preview(showBackground = true)
@Composable
fun JugadorItemPreview() {
    PMDMPractica3EquipoEsportsTheme {
        JugadorItem(
            jugador = Jugador(1, "Alexia Putellas", "La Reina", "Centrocampista")
        )
    }
}