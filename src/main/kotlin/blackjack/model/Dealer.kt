package blackjack.model

import blackjack.base.BaseHolder

class Dealer(cards: List<Card> = emptyList()) : BaseHolder() {
    private var _cards: List<Card> = cards
    override val cards: List<Card>
        get() = _cards

    override fun takeCard(card: Card) {
        _cards += card
    }
}
