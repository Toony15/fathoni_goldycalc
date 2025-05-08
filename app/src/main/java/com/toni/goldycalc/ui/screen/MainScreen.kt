package com.toni.goldycalc.ui.screen
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.toni.goldycalc.R
import com.toni.goldycalc.model.Catatan
import com.toni.goldycalc.navigation.Screen
import com.toni.goldycalc.ui.theme.GoldyCalcTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "GoldyCalc",
                        style = MaterialTheme.typography.headlineSmall
                    )
                },

                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFFDAA520),
                    titleContentColor = Color.White,
                ),
                actions = {
                    IconButton(onClick = {navController.navigate(Screen.About.route)}) {
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowRight,
                            contentDescription = stringResource(R.string.penjelasan),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    IconButton(onClick = { navController.navigate(Screen.Note.route) }) {
                        Icon(
                            imageVector = Icons.Outlined.AddCircle,
                            contentDescription = stringResource(R.string.tambah_catatan),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }



            )
        }
    ) { innerPadding ->
        ScreenContent(Modifier.padding(innerPadding))
    }
}




fun hitungHargaEmas(gram: Double): Double {
    val hargaPerGram = 1_200_000.0 // Bisa kamu update dari API nanti
    return gram * hargaPerGram
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContent(modifier: Modifier = Modifier) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    var Hitung by remember { mutableStateOf("") }
    var hasilHitung by remember { mutableStateOf<Double?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp, vertical = 90.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Golden Calculator",
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 25.dp),
            style = MaterialTheme.typography.headlineLarge,
            color = Color(0xFFFFFF00),
            textAlign = TextAlign.Center,
            maxLines = 1
        )

        Text(
            text = stringResource(id = R.string.Hitung_Emas),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = Hitung,
            onValueChange = {
                Hitung = it
                error = null
                hasilHitung = null
            },
            label = { Text(text = stringResource(R.string.Hitung_Emas)) },
            trailingIcon = { Text(text = "Gram") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            interactionSource = interactionSource,
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.Transparent,
                focusedBorderColor = if (isFocused && Hitung.isNotEmpty()) Color(0xFFFFFF00) else Color(0xFFFFD700),
                unfocusedBorderColor = Color.Gray
            )
        )
        hasilHitung?.let {
            Text(
                text = "Harga Emas: Rp${"%,.0f".format(it)}",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        // Tombol hitung
        androidx.compose.material3.Button(
            onClick = {
                val gram = Hitung.toDoubleOrNull()
                if (gram == null || gram < 1) {
                    error = "Masukkan minimal 1 gram"
                    hasilHitung = null
                } else {
                    hasilHitung = hitungHargaEmas(gram)
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFDAA520), // warna coklat keemasan
                contentColor = Color.White
            )
        ) {
            Text("Hitung")
        }

        // Tampilkan hasil atau error
        error?.let {
            Text(text = it, color = Color.Red)
        }


    }
}

@Preview(showBackground = true)
@Composable
fun MainAppPreview() {
    GoldyCalcTheme {
        MainScreen(rememberNavController())
    }
}
