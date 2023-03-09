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

    override fun decideGameResult(otherPlayer: Player) {
        val otherPlayerCardsSum: Int = otherPlayer.cards.sum()
        val cardsSum = cards.sum()

        gameResult = when {
            cardsSum > MAX_SUM_NUMBER -> GameResult.LOSE
            otherPlayerCardsSum > MAX_SUM_NUMBER -> GameResult.WIN
            otherPlayerCardsSum > cardsSum -> GameResult.LOSE
            otherPlayerCardsSum == cardsSum -> GameResult.DRAW
            else -> GameResult.WIN
        }
    }

    fun updateGameResult(newGameResult: GameResult) {
        gameResult = newGameResult
    }
}
