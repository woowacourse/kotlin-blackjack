package blackjack.model

import blackjack.model.WinningResult.Companion.dealerResult

class Dealer(val firstCard : List<Card>,val name :String = "딜러" ,) : Participant(firstCard) {
    fun drawUntilFinished(cardDeck: CardDeck): Int {
        var count = INITIAL_RESULT_COUNT

        while (hand.score() <= DEALER_DRAW_CRITERIA && !hand.isBust()) {
            draw(cardDeck)
            count++
        }

        return count
    }

    fun result(players: Players): Map<WinningResult, Int> {
        val result = WinningResult.entries.associateWith { INITIAL_SCORE }.toMutableMap()

        players.value.forEach { player ->
            val winningResult = WinningResult.from(this,player).dealerResult()
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
