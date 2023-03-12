package blackjack.domain.player

import blackjack.domain.card.Cards

class Dealer(
    name: String = "딜러",
    cards: Cards = Cards()
) : Player(name, cards) {

    override fun canHit(): Boolean = cards.sum() <= MIN_SUM_NUMBER

    companion object {
        const val MIN_SUM_NUMBER = 16
    }
}
