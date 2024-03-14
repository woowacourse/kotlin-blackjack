package blackjack.model.domain

import blackjack.model.entitiy.GameResult
import blackjack.model.store.Store

class Judge(
    private val store: Store,
) {
    fun getGameResults(): List<GameResult> {
        val dealerTotal = store.dealerInfo.sumCardValues()

        return store.playersInfo.map { playerStat ->
            val playerTotal = playerStat.sumCardValues()

            when {
                dealerTotal > CRITERIA_NUMBER && playerTotal > CRITERIA_NUMBER -> GameResult.DRAW
                dealerTotal > CRITERIA_NUMBER -> GameResult.WIN
                playerTotal > CRITERIA_NUMBER -> GameResult.LOSE
                playerTotal > dealerTotal -> GameResult.WIN
                dealerTotal > playerTotal -> GameResult.LOSE
                else -> GameResult.DRAW
            }
        }
    }

    companion object {
        private const val CRITERIA_NUMBER = 21
    }
}
