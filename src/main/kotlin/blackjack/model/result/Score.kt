package blackjack.model.result

import blackjack.model.config.GameRule.BLACK_JACK_SCORE

@JvmInline
value class Score(val point: Int) : Comparable<Score> {
    override fun compareTo(other: Score): Int = point - other.point

    fun determineWinning(dealerScore: Score): WinningResultStatus =
        when {
            this > BLACK_JACK_SCORE -> WinningResultStatus.DEFEAT
            dealerScore > BLACK_JACK_SCORE -> WinningResultStatus.VICTORY
            dealerScore > this -> WinningResultStatus.DEFEAT
            this > dealerScore -> WinningResultStatus.VICTORY
            dealerScore == this -> WinningResultStatus.DRAW
            else -> WinningResultStatus.VICTORY
        }
}
