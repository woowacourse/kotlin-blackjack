package blackjack.model

open class Player(
    open val name: String,
    open val cards: Cards = Cards(emptyList()),
) {
    open fun appendCard(card: Card) {
        cards.add(card)
    }
}
