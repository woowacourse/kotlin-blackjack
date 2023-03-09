package blackjack.domain.gameResult

import blackjack.domain.card.Cards
import blackjack.domain.card.CardsState

enum class GameResult(val profitRate: Double) {

    BLACKJACK_WIN(1.5),
    WIN(1.0),
    LOSE(-1.0),
    DRAW(0.0);

    operator fun not(): GameResult = when (this) {
        BLACKJACK_WIN -> LOSE
        WIN -> LOSE
        LOSE -> WIN
        DRAW -> DRAW
    }

    companion object {

        fun valueOf(playerCards: Cards, dealerCards: Cards): GameResult = when (playerCards.state) {
            is CardsState.BlackJack -> CardsState.BlackJack.decideGameResult(dealerCards.state)
            is CardsState.Burst -> CardsState.Burst.decideGameResult(dealerCards.state)
            is CardsState.Running -> CardsState.Running.decideGameResult(playerCards.getTotalCardsScore(), dealerCards)
        }
    }
}
