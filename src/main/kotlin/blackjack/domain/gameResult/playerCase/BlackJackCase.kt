package blackjack.domain.gameResult.playerCase

import blackjack.domain.card.CardsState
import blackjack.domain.gameResult.GameResult

enum class BlackJackCase(val gameResult: GameResult) {

    WIN(GameResult.BLACKJACK_WIN),
    DRAW(GameResult.DRAW);

    companion object {

        fun valueOf(dealerCardsState: CardsState): BlackJackCase {
            return when (dealerCardsState) {
                is CardsState.BlackJack -> DRAW
                else -> WIN
            }
        }
    }
}
