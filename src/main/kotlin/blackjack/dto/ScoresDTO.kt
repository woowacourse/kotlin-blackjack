package blackjack.dto

data class ScoresDTO(
    val dealerScore: ScoreDTO,
    val playersScore: List<ScoreDTO>,
)
