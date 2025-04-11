package com.example.coinapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.coinapp.models.Asset
import com.example.coinapp.viewModels.AssetsListViewModel

@Composable
fun AssetsList(viewModel: AssetsListViewModel = hiltViewModel() ) {
    val assets by viewModel.assets.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.onBackground)
    ) {
        items(assets) { asset ->
            AssetRow(asset)
        }
    }
}
@Composable
fun AssetRow(asset: Asset){

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.Black)
    ){
        Box(
            modifier = Modifier
                .padding(horizontal = 8.dp)
        ){
            if(LocalInspectionMode.current)
            {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(30.dp)
                )
            }
            else
            {
                AsyncImage(
                    model = "https://assets.coincap.io/assets/icons/${asset.symbol.lowercase()}@2x.png",
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
        }

        Column (
            modifier = Modifier
                .fillMaxWidth(0.3f)
        ){
            Text(
                text = asset.symbol,
                fontSize = 18.sp,
                color = Color.White
            )
            Text(
                text = asset.name,
                fontSize = 14.sp,
                color = Color.White
            )
        }

        Column {
            Row()
            {
                Icon(
                    imageVector = if (asset.percent >= 0) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = null,
                    tint = if (asset.percent >= 0) Color.Green else Color.Red,
                    //modifier = Modifier.padding(horizontal = 8.dp)
                )

                Text(
                    text = "${asset.percent}%",
                    fontSize = 16.sp,
                    color = if (asset.percent >= 0) Color.Green else Color.Red,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
        Text(
            text = "$${asset.price}",
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 16.dp),
            color = Color.White
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun AssetRowPreview(){
    AssetsList()
}
