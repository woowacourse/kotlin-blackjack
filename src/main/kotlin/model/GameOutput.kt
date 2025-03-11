package model

data class GameOutput(
    val dealerWins: Int,
    val dealerLosses: Int,
    val playerResults: List<PlayerResult>,
)
