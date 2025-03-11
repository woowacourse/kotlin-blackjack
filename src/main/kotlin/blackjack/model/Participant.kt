package blackjack.model

abstract class Participant(
    val name: String,
    val cards: Cards = Cards(emptyList()),
) {
    fun appendCard(cardDeck: CardDeck) {
        val card = cardDeck.pickCard()
        cards.add(card)
    }

    fun isBlackjack(): Boolean = cards.status == CardsStatus.BLACKJACK

    fun isBust(): Boolean = cards.status == CardsStatus.BUST
}
