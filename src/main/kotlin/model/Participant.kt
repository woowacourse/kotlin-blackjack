package model

abstract class Participant(private val hand: Hand) {
    val cards: List<Card> get() = hand.handCards

    abstract fun decideToHit(): Boolean

    fun getScore(): Int = hand.getScore()

    fun receiveCards(getCards: (Int) -> List<Card>) {
        val count = if (cards.isEmpty()) INITIAL_DRAW_COUNT else DEFAULT_DRAW_COUNT
        hand.addCards(getCards(count))
    }

    fun getHand(): Hand = hand

    companion object {
        private const val INITIAL_DRAW_COUNT = 2
        private const val DEFAULT_DRAW_COUNT = 1
    }
}
