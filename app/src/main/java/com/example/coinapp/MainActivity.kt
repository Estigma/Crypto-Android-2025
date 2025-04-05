package com.example.coinapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.coinapp.models.Asset
import com.example.coinapp.ui.theme.CoinAppTheme
import com.example.coinapp.views.AssetRow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoinAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    )
                    {
                        AssetRow(
                            Asset(
                                id = "BTC",
                                name = "Bitcoin",
                                symbol = "BTC",
                                price = "82697.96",
                                percentage = 5.45
                            )
                        )
                        AssetRow(
                            Asset(
                                id = "ETH",
                                name = "Etherium",
                                symbol = "ETH",
                                price = "22697.96",
                                percentage = -1.45
                            )
                        )
                    }
                }
            }
        }
    }
}