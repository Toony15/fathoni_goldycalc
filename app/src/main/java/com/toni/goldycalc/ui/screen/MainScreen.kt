package com.toni.goldycalc.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.toni.goldycalc.R
import com.toni.goldycalc.ui.theme.GoldyCalcTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen () {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "GoldyCalc",
                        style = MaterialTheme.typography.headlineSmall
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor =  Color(0xFFFFD700),
                    titleContentColor = androidx.compose.ui.graphics.Color.White,
                ),

                )
        },


        ) { innerPadding ->
        ScreenContent(Modifier.padding(innerPadding))

    }
}
@Composable
fun ScreenContent ( modifier: Modifier = Modifier) {
    var Hitung by remember { mutableStateOf("") }
    Column (
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally



    ){
        Text(
            text = "Golden Calculator",
            modifier = modifier,

            style = MaterialTheme.typography.headlineLarge,
            color = Color(0xFFFFFF00)
        )
    }
    Column (
        modifier = Modifier.fillMaxSize().padding(75.dp),
        verticalArrangement = Arrangement.spacedBy(75.dp),
    ) {
       Text(
           text = stringResource(id = R.string.Hitung_Emas),
           style = MaterialTheme.typography.bodyLarge,
           modifier = Modifier.fillMaxWidth()
       )
        OutlinedTextField(
            value = Hitung,
            onValueChange = { Hitung = it},
            label = { Text(text = stringResource(R.string.Hitung_Emas)) },
            trailingIcon = { Text(text = "Gram") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }

}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GoldyCalcTheme {
        MainScreen()
    }
}