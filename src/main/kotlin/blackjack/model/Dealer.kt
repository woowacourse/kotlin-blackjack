package blackjack.model

class Dealer : Participant() {
    fun drawUntilFinished(cardDeck: CardDeck): Int {
        var count = INITIAL_RESULT_COUNT

        while (score() <= DEALER_DRAW_CRITERIA && !isBust()) {
            draw(cardDeck)
            count++
        }

        return count
    }

    fun result(playerScores: List<Int>): Map<WinningResult, Int> {
        val result = WinningResult.entries.associateWith { INITIAL_SCORE }.toMutableMap()

        playerScores.forEach { playerScore ->
            val winningResult = WinningResult.from(score(), playerScore)
            result[winningResult] = result.getOrDefault(winningResult, INITIAL_SCORE) + ADDITIONAL_RESULT_COUNT
        }

        return result.toMap()
    }

    companion object {
        private const val INITIAL_RESULT_COUNT = 0
        private const val DEALER_DRAW_CRITERIA = 16
        private const val INITIAL_SCORE = 0
        private const val ADDITIONAL_RESULT_COUNT = 1
    }
}
