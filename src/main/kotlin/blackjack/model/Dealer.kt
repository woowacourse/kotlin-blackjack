package blackjack.model

class Dealer(val name: String = DEALER_NAME, hand: Hand) : Participant(hand) {
    var drawCount = 0
    fun drawUntilFinished(cardDeck: CardDeck) {
        while (hand.score() <= DEALER_DRAW_CRITERIA && !hand.isBust()) {
            drawCount++
            draw(cardDeck)
        }
    }

    fun getAdditionalDrawCount(): Int {
        return drawCount
    }

    fun getWinDrawLossResult(players: Players): Map<WinningResult, Int> {
        val result = WinningResult.entries.associateWith { INITIAL_SCORE }.toMutableMap()

        players.value.forEach { player ->
            val winningResult = CalculateResult.getDealerResult(this, player)
            result[winningResult] = result.getOrDefault(winningResult, INITIAL_SCORE) + ADDITIONAL_RESULT_COUNT
        }

        return result.toMap()
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_DRAW_CRITERIA = 16
        private const val INITIAL_SCORE = 0
        private const val ADDITIONAL_RESULT_COUNT = 1
    }
}
