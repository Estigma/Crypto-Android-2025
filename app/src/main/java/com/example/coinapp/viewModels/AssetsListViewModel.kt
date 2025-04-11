package com.example.coinapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinapp.models.Asset
import com.example.coinapp.services.CoinCapApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AssetsListViewModel @Inject constructor(
    private val apiService: CoinCapApiService
): ViewModel() {
    // TODO: add Loading
    private val _assets = MutableStateFlow<List<Asset>>(emptyList())
    val assets: StateFlow<List<Asset>> = _assets

    init {
        fetchAssets()
    }

    // TODO: add error

    private fun fetchAssets(){
        // isloading = true
        viewModelScope.launch {
            try{
                val result = apiService.getAssets().data
                val mappedAassets = result.map{ assetResponse ->

                    val price = String.format("%.2f", assetResponse.priceUsd.toDouble())
                    val percentage = String.format("%.2f", assetResponse.changePercent24Hr.toDouble()).toDouble()
                    Asset(
                        assetResponse.id,
                        assetResponse.name,
                        assetResponse.symbol,
                        price,
                        percentage
                    )
                }

                _assets.value = mappedAassets
                // isloading = false
            } catch (e: Exception){
                // Handle error
                println("Error: ${e.message}")
                // isloading = false
            }
        }
    }
}