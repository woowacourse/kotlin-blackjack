package blackjack.state

import blackjack.model.CardHolder
import blackjack.model.Dealer
import blackjack.model.GameResult

sealed class BlackjackState {
    abstract val isFinished: Boolean

    abstract fun calculateGameResult(
        self: CardHolder,
        opponent: CardHolder,
    ): GameResult
}

class Blackjack : BlackjackState() {
    override val isFinished = true

    override fun calculateGameResult(
        self: CardHolder,
        opponent: CardHolder,
    ): GameResult {
        if (opponent.getState() is Blackjack) return GameResult(0, 0)
        return GameResult(1, 0)
    }
}

class Bust : BlackjackState() {
    override val isFinished = true

    override fun calculateGameResult(
        self: CardHolder,
        opponent: CardHolder,
    ): GameResult {
        if (self is Dealer && opponent.getState() is Bust) return GameResult(1, 0)
        return GameResult(0, 1)
    }
}

class Normal : BlackjackState() {
    override val isFinished = false

    override fun calculateGameResult(
        self: CardHolder,
        opponent: CardHolder,
    ): GameResult {
        return when {
            opponent.getState() is Bust || self.hand.calculate() > opponent.hand.calculate() -> GameResult(1, 0)
            opponent.getState() is Blackjack || self.hand.calculate() < opponent.hand.calculate() -> GameResult(0, 1)
            else -> GameResult(0, 0)
        }
    }
}
