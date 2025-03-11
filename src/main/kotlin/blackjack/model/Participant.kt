package blackjack.model

abstract class Participant(
    open val name: String,
    open val cards: Cards = Cards(emptyList()),
) {
    fun isBlackjack(): Boolean = cards.status == CardsStatus.BLACKJACK

    fun isBust(): Boolean = cards.status == CardsStatus.BUST
}
