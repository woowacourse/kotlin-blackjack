package blackjack.domain.player

import blackjack.domain.card.Cards
import blackjack.domain.result.GameResult

class Dealer(
    name: String = "딜러",
    cards: Cards = Cards()
) : Player(name, cards) {

    val results: MutableMap<GameResult, Int> = GameResult.values().associateWith { 0 }.toMutableMap()

    override fun canHit(): Boolean = cards.sum() <= MIN_SUM_NUMBER

    override fun decideGameResult(otherPlayer: Player) {
        val otherPlayerCardsSum: Int = otherPlayer.cards.sum()
        val cardsSum = cards.sum()

        val gameResult = when {
            cardsSum > MAX_SUM_NUMBER -> GameResult.LOSE
            otherPlayerCardsSum > MAX_SUM_NUMBER -> GameResult.WIN
            otherPlayerCardsSum > cardsSum -> GameResult.LOSE
            otherPlayerCardsSum == cardsSum -> GameResult.DRAW
            else -> GameResult.WIN
        }

        results[gameResult] =
            results[gameResult]?.plus(1) ?: throw IllegalArgumentException()
    }

    private fun reversGameResult(gameResult: GameResult): GameResult = when (gameResult) {
        GameResult.WIN -> GameResult.LOSE
        GameResult.LOSE -> GameResult.WIN
        else -> GameResult.DRAW
    }

    fun decideGameResult(participants: Participants) {
        participants.values.forEach {
            val gameResult = reversGameResult(it.gameResult)
            results[gameResult] = results[gameResult]?.plus(1) ?: throw IllegalArgumentException()
        }
    }

    companion object {
        const val MIN_SUM_NUMBER = 16
    }
}
