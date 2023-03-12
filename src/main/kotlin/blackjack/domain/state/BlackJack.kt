package blackjack.domain.state

import blackjack.domain.card.Cards
import blackjack.domain.state.Outcome.WIN_WITH_BLACKJACK

class BlackJack(cards: Cards = Cards()) : State(cards) {
    init {
        require(cards.size == 2) { ERROR_BLACKJACK_CARD_COUNT }
        require(cards.calculateScore().isBlackJack) { ERROR_BLACKJACK_SCORE }
    }

    override fun matchWith(otherState: State): Outcome = WIN_WITH_BLACKJACK

    companion object {
        private const val ERROR_BLACKJACK_CARD_COUNT = "BlackJack은 2장의 카드로 이루어져야 합니다."
        private const val ERROR_BLACKJACK_SCORE = "BlackJack은 21점이어야 합니다."
    }
}
