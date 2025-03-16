package blackjack.domain.participant

import blackjack.domain.BlackJackGame.Companion.BUST_STANDARD
import blackjack.domain.BlackJackGame.Companion.CARD_COUNT_OF_PLAYER_MUST_INITIAL_OPEN
import blackjack.domain.card.TrumpCard

class Player(
    private val state: PlayerState,
) : Participant() {
    val name: String get() = state.name
    val money: Int get() = state.money.value

    override fun canHit(): Boolean {
        return totalScore() <= BUST_STANDARD
    }

    override fun getInitialCards(): Set<TrumpCard> {
        return cards.items.take(CARD_COUNT_OF_PLAYER_MUST_INITIAL_OPEN).toSet()
    }

    fun profit(dividend: Double) = state.money * dividend
}
