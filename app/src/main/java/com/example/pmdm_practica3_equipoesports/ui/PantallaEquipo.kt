package com.example.pmdm_practica3_equipoesports.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.material3.OutlinedTextField
@Composable
fun PantallaEquipo() {
    // Lista observable: al añadir/borrar/editar, la UI se recompone sola
    val jugadores = JugadoresEjemplo.jugadores
    // null = sin diálogo, un jugador = mostrar diálogo para ese jugador
    var jugadorAEliminar by remember { mutableStateOf<Jugador?>(null) }
    var jugadorAEditar by remember { mutableStateOf<Jugador?>(null) }

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
        // ... Text("TEAM TITANS") ...

        FormularioJugador(
            onAnadirJugador = { nombre, nickname, rol ->
                // id único: el mayor actual + 1 (nunca size + 1)
                val nuevoId = (jugadores.maxOfOrNull { it.id } ?: 0) + 1
                jugadores.add(Jugador(nuevoId, nombre, nickname, rol))
            }
        )
        Spacer(modifier = Modifier.height(8.dp))


        // weight(1f): la lista ocupa el espacio restante (y deja sitio
        // al formulario que añadiremos en el Bloque 2)
        ListaJugadores(
            jugadores = jugadores,
            onEliminar = { jugador -> jugadorAEliminar = jugador },  // abre el diálogo
            onEditar = { jugador -> jugadorAEditar = jugador },
            modifier = Modifier.weight(1f)
        )

        // El diálogo existe solo mientras jugadorAEliminar no sea null
        jugadorAEliminar?.let { jugador ->
            DialogoEliminar(
                jugador = jugador,
                onConfirmar = {
                    jugadores.remove(jugador)   // la lista se actualiza sola
                    jugadorAEliminar = null      // cierra el diálogo
                },
                onCancelar = { jugadorAEliminar = null }
            )
        }
        jugadorAEditar?.let { jugador ->
            DialogoEditar(
                jugador = jugador,
                onGuardar = { jugadorEditado ->
                    val posicion = jugadores.indexOfFirst { it.id == jugadorEditado.id }
                    if (posicion != -1) {
                        jugadores[posicion] = jugadorEditado   // sustituye → la lista se actualiza
                    }
                    jugadorAEditar = null
                },
                onCancelar = { jugadorAEditar = null }
            )
        }
    }
}

// ---------- LISTA (Actividad 3) ----------
@Composable
fun ListaJugadores(
    jugadores: List<Jugador>,
    onEliminar: (Jugador) -> Unit,
    onEditar: (Jugador) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        // key = id → cada fila tiene identidad estable al borrar/editar
        items(jugadores, key = { it.id }) { jugador ->
            JugadorItem(jugador = jugador,
                onEliminarClick = { onEliminar(jugador) }, // avisa de QUÉ jugador
                onEditarClick = { onEditar(jugador) }
            )
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
fun JugadorItem(
    jugador: Jugador,
    onEliminarClick: () -> Unit,
    onEditarClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
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

        // weight(1f): los textos ocupan el hueco y empujan los botones a la derecha
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        ) {
            Text(
                text = jugador.nickname,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1B1F3B)
            )
            Text(text = jugador.nombre, fontSize = 15.sp, color = Color.DarkGray)
            Text(text = jugador.rol, fontSize = 14.sp, color = MaterialTheme.colorScheme.primary)
        }

        // Botones de operaciones (en el Bloque 4 añadiremos "Editar" aquí)
        Column {
            TextButton(onClick = onEditarClick) {
                Text("Editar")
            }
            TextButton(onClick = onEliminarClick) {
                Text("Eliminar", color = MaterialTheme.colorScheme.error)
            }
        }
    }
}
// ---------- DIÁLOGO DE CONFIRMACIÓN (Actividad 4) ----------
@Composable
fun DialogoEliminar(
    jugador: Jugador,
    onConfirmar: () -> Unit,
    onCancelar: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCancelar,   // tocar fuera o pulsar Atrás = cancelar
        title = { Text("Eliminar jugador") },
        text = { Text("¿Estás seguro de que deseas eliminar a ${jugador.nickname}?") },
        confirmButton = {
            TextButton(onClick = onConfirmar) {
                Text("Eliminar", color = MaterialTheme.colorScheme.error)
            }
        },
        dismissButton = {
            TextButton(onClick = onCancelar) {
                Text("Cancelar")
            }
        }
    )
}
// ---------- DIÁLOGO DE EDICIÓN (Actividad 5) ----------
@Composable
fun DialogoEditar(
    jugador: Jugador,
    onGuardar: (Jugador) -> Unit,
    onCancelar: () -> Unit
) {
    // Los campos empiezan con los datos actuales del jugador
    var nombre by remember { mutableStateOf(jugador.nombre) }
    var nickname by remember { mutableStateOf(jugador.nickname) }
    var rol by remember { mutableStateOf(jugador.rol) }
    var mostrarError by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onCancelar,
        title = { Text("Editar jugador") },
        text = {
            Column {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it; mostrarError = false },
                    label = { Text("Nombre") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = nickname,
                    onValueChange = { nickname = it; mostrarError = false },
                    label = { Text("Nickname") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = rol,
                    onValueChange = { rol = it; mostrarError = false },
                    label = { Text("Rol") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                if (mostrarError) {
                    Text(
                        text = "Rellena todos los campos",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                if (nombre.isBlank() || nickname.isBlank() || rol.isBlank()) {
                    mostrarError = true
                } else {
                    // copy() mantiene el id original y cambia el resto
                    onGuardar(
                        jugador.copy(
                            nombre = nombre.trim(),
                            nickname = nickname.trim(),
                            rol = rol.trim()
                        )
                    )
                }
            }) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = onCancelar) { Text("Cancelar") }
        }
    )
}
// ---------- PREVIEW ----------
@Preview(showBackground = true)
@Composable
fun JugadorItemPreview() {
    PMDMPractica3EquipoEsportsTheme {
        JugadorItem(onEliminarClick = {}, onEditarClick = {},
            jugador = Jugador(1, "Alexia Putellas", "La Reina", "Centrocampista")
        )
    }
}