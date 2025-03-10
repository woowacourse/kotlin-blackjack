package blackjack.domain.model

abstract class Participant {
    abstract val name: String
    protected abstract var hands: Hands

    fun getScore() = hands.getScore()

    fun isStartCardCount() = hands.isStartCardCount()

    fun showCards(count: Int = hands.cards.count()): List<Card> = hands.extractCards(count)

    fun acceptCard(card: Card) {
        hands = hands.nextHand(card)
    }

    fun isBust(): Boolean = hands.isBust()
}
