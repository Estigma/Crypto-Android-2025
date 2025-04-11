package  com.example.coinapp.models
import kotlinx.serialization.Serializable

@Serializable
data class Asset(
    val id: String,
    val name: String,
    val symbol: String,
    val price: String,
    val percent: Double
)