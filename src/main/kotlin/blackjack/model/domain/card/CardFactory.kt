package blackjack.model.domain.card

class CardFactory {
    fun makeCard(): ArrayDeque<Card> {
        val cards = Card.CARDDECK.values.toMutableList()
        return ArrayDeque(cards.shuffled())
    }
}
