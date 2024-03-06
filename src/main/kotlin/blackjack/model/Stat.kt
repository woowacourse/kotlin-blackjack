package blackjack.model

data class Stat(
    val name: String,
    val total: Int,
    val cards: Set<Card>,
)
