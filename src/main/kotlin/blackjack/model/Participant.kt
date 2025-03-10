package blackjack.model

abstract class Participant(
    open val name: String,
    open val cards: Cards = Cards(emptyList()),
) {
    fun isBlackjack(firstTurn: Boolean): Boolean = cards.isBlackjack(firstTurn)

    fun isBust(): Boolean = cards.isBust()
}
