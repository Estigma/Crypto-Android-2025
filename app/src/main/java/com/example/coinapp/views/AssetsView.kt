package com.example.coinapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.coinapp.models.Asset

@Composable
fun AssetRow(asset: Asset){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            //.background(Color.Gray)
    ){

//        Icon(
//            imageVector = Icons.Filled.CheckCircle,
//            contentDescription = null,
//            tint = if( asset.percentage >= 0 ) Color.Green else Color.Red,
//            modifier = Modifier.padding(horizontal = 8.dp)
//        )

        AsyncImage(
            model = "https://assets.coincap.io/assets/icons/${asset.symbol.lowercase()}@2x.png",
            contentDescription = null,
            modifier = Modifier.padding(horizontal = 12.dp)
        )


        Column (
            modifier = Modifier
                .fillMaxWidth(0.3f)
        ){
            Text(
                text = asset.symbol,
                fontSize = 18.sp
            )
            Text(
                text = asset.name,
                fontSize = 14.sp
            )
        }

        //Spacer(modifier = Modifier.weight(1f))

//        Icon(
//            imageVector = Icons.Filled.KeyboardArrowUp,
//            contentDescription = null,
//            tint = Color.Green,
//            modifier = Modifier.padding(horizontal = 16.dp)
//        )
        Column {
            Row()
            {
                Icon(
                    imageVector = if (asset.percentage >= 0) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = null,
                    tint = if (asset.percentage >= 0) Color.Green else Color.Red,
                    //modifier = Modifier.padding(horizontal = 8.dp)
                )

                Text(
                    text = "${asset.percentage}%",
                    fontSize = 16.sp,
                    color = if (asset.percentage >= 0) Color.Green else Color.Red,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
        Text(
            text = "$${asset.price}",
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

//        Column {
//            Text(text = "82697.96")
//        }
    }


}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun AssetRowPreview(){
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
