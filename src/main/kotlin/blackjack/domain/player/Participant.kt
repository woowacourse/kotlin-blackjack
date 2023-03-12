package blackjack.domain.player

import blackjack.domain.card.Cards

class Participant(
    name: String,
    cards: Cards = Cards()
) : Player(name, cards) {

    override fun canHit(): Boolean = cards.sum() < MAX_SUM_NUMBER
}
