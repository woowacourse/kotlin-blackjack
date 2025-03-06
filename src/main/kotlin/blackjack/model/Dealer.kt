package blackjack.model

class Dealer(
    override val name: String = "딜러",
    override val cards: Cards = Cards(mutableListOf()),
) : Player(name) {
    override fun appendCard(card: Card) {
        cards.add(card)
    }
}
