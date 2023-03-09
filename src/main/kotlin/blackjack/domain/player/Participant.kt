package blackjack.domain.player

import blackjack.domain.card.Cards
import blackjack.domain.result.GameResult

class Participant(
    name: String,
    cards: Cards = Cards()
) : Player(name, cards) {

    lateinit var gameResult: GameResult
        private set

    override fun canHit(): Boolean = cards.sum() < MAX_SUM_NUMBER

    fun updateGameResult(newGameResult: GameResult) {
        gameResult = newGameResult
    }
}
