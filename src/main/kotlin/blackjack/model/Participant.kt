package blackjack.model

abstract class Participant(
    val name: String,
    val hand: Hand = Hand(emptyList()),
) {
    abstract val money: Money

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

    fun isBlackjack(): Boolean = hand.status == CardsStatus.BLACKJACK

    fun isBust(): Boolean = hand.status == CardsStatus.BUST

    abstract fun canHit(): Boolean

    abstract fun updateProfit(opponent: Participant)

    companion object {
        private const val STANDARD_PICK_COUNT = 1
    }
}
