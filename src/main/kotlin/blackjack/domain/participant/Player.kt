package blackjack.domain.participant

import blackjack.domain.BlackJackGame.Companion.BUST_STANDARD
import blackjack.domain.BlackJackGame.Companion.CARD_COUNT_OF_PLAYER_MUST_INITIAL_OPEN
import blackjack.domain.card.TrumpCard

class Player(
    val name: String,
) : Participant() {
    override fun isDrawable(): Boolean {
        return totalScore() <= BUST_STANDARD
    }

    override fun getInitialCards(): Set<TrumpCard> {
        return cards.items.take(CARD_COUNT_OF_PLAYER_MUST_INITIAL_OPEN).toSet()
    }
}
