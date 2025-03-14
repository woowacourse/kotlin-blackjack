package blackjack.model

import blackjack.model.ResultType.BLACKJACK
import blackjack.model.ResultType.LOSS
import blackjack.model.ResultType.TIE
import blackjack.model.ResultType.WIN
import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.participant.Dealer
import blackjack.model.participant.Player

object ResultCalculator {
    const val BLACKJACK_NUMBER = 21
    const val BLACKJACK_PROFIT_MULTIPLIER = 1.5
    const val LOSS_PROFIT_MULTIPLIER = -1.0
    const val TIE_PROFIT_MULTIPLIER = 0.0
    private const val ADJUST_ACE_NUMBER = 10

    fun calculate(cards: List<Card>): Int {
        var sumScore = calculateTotalScore(cards)
        var countAce = countAce(cards)
        while (countAce-- > 0) {
            if (sumScore + ADJUST_ACE_NUMBER > BLACKJACK_NUMBER) {
                break
            }
            sumScore += ADJUST_ACE_NUMBER
        }
        return sumScore
    }

    fun judgeScore(
        dealer: Dealer,
        player: Player,
    ): ResultType {
        if (player.isBust()) return LOSS
        if (player.isBlackjack()) {
            return if (dealer.isBlackjack()) {
                TIE
            } else {
                BLACKJACK
            }
        }
        if (dealer.isBust()) return WIN

        return when {
            player.score > dealer.score -> WIN
            player.score == dealer.score -> TIE
            else -> LOSS
        }
    }

    private fun calculateTotalScore(cards: List<Card>) = cards.sumOf { card -> card.number.score }

    private fun countAce(cards: List<Card>) = cards.count { it.number == CardNumber.ACE }
}
