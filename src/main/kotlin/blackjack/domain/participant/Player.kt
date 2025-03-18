package blackjack.domain.participant

import blackjack.domain.BlackJackGame.Companion.BUST_STANDARD
import blackjack.domain.BlackJackGame.Companion.CARD_COUNT_OF_PLAYER_MUST_INITIAL_OPEN
import blackjack.domain.GameResult
import blackjack.domain.GameResult.BLACKJACK
import blackjack.domain.GameResult.LOSE
import blackjack.domain.GameResult.PUSH
import blackjack.domain.GameResult.WIN
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

    override fun compare(other: Participant): GameResult {
        return when {
            hasBlackJack() && !other.hasBlackJack() -> BLACKJACK
            hasBlackJack() && other.hasBlackJack() -> PUSH
            isBust() && other.isBust() -> LOSE
            isBust() -> LOSE
            other.isBust() -> WIN
            totalScore() > other.totalScore() -> WIN
            totalScore() < other.totalScore() -> LOSE
            else -> PUSH
        }
    }

    fun profit(dividend: Double) = state.money * dividend
}
