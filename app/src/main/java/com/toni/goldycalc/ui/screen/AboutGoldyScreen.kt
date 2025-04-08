package com.toni.goldycalc.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.isFocused
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.toni.goldycalc.R

data class GoldItem(val name: String, val imageRes: Int)
val dummyGoldList = listOf(
    GoldItem("1 gram", R.drawable.gold_1g),
    GoldItem("1 gram", R.drawable.gold_3g),
    GoldItem("5 gram", R.drawable.gold_5g),
    GoldItem("10 gram", R.drawable.gold_10g),
    GoldItem("1 gram", R.drawable.gold_25g),

)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutGoldyScreen(navController: NavHostController) {
    val interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() }
    val isFocused = interactionSource.collectIsFocusedAsState().value

    var searchQuery by remember {mutableStateOf(TextFieldValue("")) }
    val filteredList = remember(searchQuery.text) {
        dummyGoldList.filter { it.name.contains(searchQuery.text, ignoreCase = true) }
    }
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
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.Kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(
                text = stringResource(R.string.penjelasan),
                modifier = Modifier
                    .padding(top = 25.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 18.sp,
                    color = Color(0xFFDAA520), // warna coklat keemasan
                    // fontFamily = FontFamily(Font(R.font.poppins_semibold)) // Aktifkan kalau mau pakai Poppins
                ),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Cari berat emas (cth: 1 gram)") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(15.dp),
                interactionSource = interactionSource,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.Transparent,
                    focusedBorderColor = if (isFocused && searchQuery.text.isNotEmpty()) Color(0xFFFFFF00) else Color(0xFFFFD700),
                    unfocusedBorderColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(16.dp))
            if (searchQuery.text.isNotEmpty()) {
                if (filteredList.isNotEmpty()) {
                    filteredList.forEach { item ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Image(
                                painter = painterResource(id = item.imageRes),
                                contentDescription = item.name,
                                modifier = Modifier.size(120.dp)
                            )
                            Text(text = item.name)
                        }
                    }
                } else {
                    Text("Tidak ditemukan", color = Color.Gray)
                }
            }

            }
        }
    }

@Composable
fun AboutScreenPreview() {
    AboutGoldyScreen(rememberNavController())
}