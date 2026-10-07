package com.example.pmdm_practica3_equipoesports

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pmdm_practica3_equipoesports.ui.theme.PMDMPractica3EquipoEsportsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()   // la app se dibuja detrás de la barra de estado
        setContent {
            PMDMPractica3EquipoEsportsTheme {
                PantallaEquipo()
            }
        }
    }
}

// ---------- PANTALLA PRINCIPAL (requisitos 1, 2, 6, 7) ----------
@Composable
fun PantallaEquipo() {
    Column(
        modifier = Modifier
            .fillMaxSize()                       // ocupa toda la pantalla
            .background(Color(0xFFEFF2F8))       // fondo ANTES del padding: también pinta tras la barra de estado
            .statusBarsPadding()                 // el contenido empieza bajo la barra de estado
    ) {
        Text(
            text = "TEAM TITANS",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally) // align() de ColumnScope
                .padding(top = 24.dp, bottom = 16.dp)
        )

        Jugador(nombre = "AlexPro", rol = "Atacante", edad = 21, nivel = 87, imagen = R.drawable.foto1)
        Separador()
        Jugador(nombre = "ShadowX", rol = "Defensor", edad = 24, nivel = 91, imagen = R.drawable.foto2)
        Separador()
        Jugador(nombre = "MartaGG", rol = "Soporte", edad = 20, nivel = 84, imagen = R.drawable.foto3)
        Separador()
        Jugador(nombre = "Destroyer", rol = "Atacante", edad = 23, nivel = 89, imagen = R.drawable.foto4
        )
    }
}

@Composable
fun Jugador(
    nombre: String,
    rol: String,
    edad: Int,
    nivel: Int,
    @DrawableRes imagen: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp) // margen exterior
            .clip(RoundedCornerShape(16.dp))  // esquinas redondeadas...
            .background(Color.White)                      // ...que afectan al fondo porque va después
            .padding(12.dp),                              // margen interior
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Box ppara apilar la foto y el texto de nivel
        Box {
            Image(
                painter = painterResource(id = imagen),
                contentDescription = "Foto de $nombre",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
            )
            Text(
                text = "$nivel",
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            )
        }

        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(
                text = nombre,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1B1F3B)
            )
            Text(text = rol, fontSize = 15.sp, color = MaterialTheme.colorScheme.primary)
            Text(text = "$edad años", fontSize = 14.sp, color = Color.DarkGray)
            Text(text = "Nivel: $nivel", fontSize = 14.sp, color = Color.DarkGray)
        }
    }
}

@Composable
fun Separador() {
    HorizontalDivider(
        modifier = Modifier.padding(horizontal = 32.dp, vertical = 4.dp),
        thickness = 1.dp,
        color = Color(0xFFB0B8C8)
    )
}

@Preview(showBackground = true)
@Composable
fun JugadorPreview() {
    PMDMPractica3EquipoEsportsTheme {
        Jugador(nombre = "AlexPro", rol = "Atacante", edad = 21, nivel = 87, imagen = R.drawable.foto1)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PantallaEquipoPreview() {
    PMDMPractica3EquipoEsportsTheme {
        PantallaEquipo()
    }
}

