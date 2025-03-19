package blackjack.model

abstract class Participant(
    val name: String,
    val hand: Hand = Hand(emptyList()),
) {
    abstract val firstOpenedCards: List<Card>

    fun pickCard(
        cardDeck: CardDeck,
        times: Int = STANDARD_PICK_COUNT,
    ) {
        repeat(times) {
            val card = cardDeck.pickCard()
            hand.add(card)
        }
    }

    fun getScore(): Int = hand.getScore()

    abstract fun canHit(): Boolean

    companion object {
        private const val STANDARD_PICK_COUNT = 1
    }
}
