package com.example.pmdm_practica3_equipoesports.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

// ---------- FORMULARIO (Actividad 2) ----------
@Composable
fun FormularioJugador(
    onAnadirJugador: (nombre: String, nickname: String, rol: String) -> Unit,
    modifier: Modifier = Modifier
) {
    // rememberSaveable: los textos sobreviven al girar la pantalla
    var nombre by rememberSaveable { mutableStateOf("") }
    var nickname by rememberSaveable { mutableStateOf("") }
    var rol by rememberSaveable { mutableStateOf("") }
    var mostrarError by rememberSaveable { mutableStateOf(false) }

    Column(modifier = modifier.padding(horizontal = 16.dp)) {
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

        // Mensaje de error solo si la validación ha fallado
        if (mostrarError) {
            Text(
                text = "Rellena todos los campos",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Button(
            onClick = {
                if (nombre.isBlank() || nickname.isBlank() || rol.isBlank()) {
                    mostrarError = true                       // 3. validar
                } else {
                    onAnadirJugador(nombre.trim(), nickname.trim(), rol.trim()) // 4. añadir
                    nombre = ""                               // 6. limpiar campos
                    nickname = ""
                    rol = ""
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text("Añadir jugador")
        }
    }
}