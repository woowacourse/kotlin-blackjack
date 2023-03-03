package blackjack.dto

data class ScoreDTO(
    val name: String,
    val hand: List<String>,
    val score: Int,
)
