package blackjack.domain.state

import blackjack.domain.card.Cards
import blackjack.domain.state.Outcome.DRAW
import blackjack.domain.state.Outcome.WIN
import blackjack.domain.state.Outcome.WIN_WITH_BLACKJACK

class BlackJack(cards: Cards = Cards()) : State(cards) {
    init {
        require(cards.calculateScore().isBlackJack) { ERROR_BLACKJACK_SCORE }
    }

    override fun matchWith(otherState: State): Outcome = when {
        this.size == 2 -> WIN_WITH_BLACKJACK
        otherState is BlackJack -> DRAW
        else -> WIN
    }

    companion object {
        private const val ERROR_BLACKJACK_SCORE = "BlackJack은 21점이어야 합니다."
    }
}
