package blackjack.domain.player

import blackjack.domain.card.Cards
import blackjack.domain.result.GameResult

class Dealer(
    name: String = "딜러",
    cards: Cards = Cards()
) : Player(name, cards) {

    override fun canHit(): Boolean = cards.sum() <= MIN_SUM_NUMBER

    private fun reversGameResult(gameResult: GameResult): GameResult = when (gameResult) {
        GameResult.WIN -> GameResult.LOSE
        GameResult.LOSE -> GameResult.WIN
        else -> GameResult.DRAW
    }

    companion object {
        const val MIN_SUM_NUMBER = 16
    }
}
