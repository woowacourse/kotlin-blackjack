package blackjack.model

import blackjack.model.Participant.Player

object Calculator {
    private const val BONUS_SCORE_CRITERIA = 11
    private const val BONUS_SCORE = 10
    private const val BLACKJACK_REVENUE_MULTIPLY = 1.5
    private const val TIE_REVENUE_AMOUNT = 0.0
    private const val DEFEAT_REVENUE_MULTIPLY = -1.0

    fun calculateScore(cards: Set<Card>): Int {
        val score = cards.sumOf { card -> card.number.value }
        val bonusScore = calculateBonusScore(cards, score)
        return score + bonusScore
    }

    fun calculatePlayerRevenue(
        player: Player,
        gameResult: GameResult,
    ): Double {
        var bettingAmount = player.playerInformation.bettingAmount.amount
        bettingAmount =
            when (gameResult) {
                GameResult.WIN -> {
                    if (player.gameInformation.state == GameState.Finished.BLACKJACK) {
                        bettingAmount * BLACKJACK_REVENUE_MULTIPLY
                    } else {
                        bettingAmount
                    }
                }

                GameResult.DEFEAT -> bettingAmount * DEFEAT_REVENUE_MULTIPLY
                GameResult.TIE -> TIE_REVENUE_AMOUNT
            }
        return bettingAmount
    }

    private fun calculateBonusScore(
        cards: Set<Card>,
        score: Int,
    ): Int {
        var bonusScore = 0
        var totalScore = score
        val numberOfAce = cards.count { card -> card.number == CardNumber.ACE }
        repeat(numberOfAce) {
            if (totalScore <= BONUS_SCORE_CRITERIA) {
                totalScore += BONUS_SCORE
                bonusScore += BONUS_SCORE
            }
        }
        return bonusScore
    }
}
