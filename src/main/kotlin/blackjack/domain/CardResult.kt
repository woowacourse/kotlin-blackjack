package blackjack.domain

data class CardResult(
    val participant: Participant,
    val cards: List<Card>,
    val scoreSum: Int,
)
