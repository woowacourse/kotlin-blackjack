package blackjack.model

import blackjack.model.ResultType.LOSS
import blackjack.model.ResultType.TIE
import blackjack.model.ResultType.WIN
import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.participant.Dealer
import blackjack.model.participant.Player

object ResultCalculator {
    const val BLACKJACK_NUMBER = 21
    private const val ADJUST_ACE_NUMBER = 10

    fun adjustScore(cards: List<Card>): Int {
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
        return when {
            player.isBust() -> LOSS
            player.isBlackjack() ->
                when {
                    dealer.isBust() || !dealer.isBlackjack() -> WIN
                    else -> TIE
                }

            dealer.isBust() -> WIN
            dealer.isBlackjack() -> LOSS
            else -> {
                val dealerScore = adjustScore(dealer.cards)
                val playerScore = adjustScore(player.cards)
                when {
                    playerScore > dealerScore -> WIN
                    playerScore == dealerScore -> TIE
                    else -> LOSS
                }
            }
        }
    }

    fun calculateTotalScore(cards: List<Card>) = cards.sumOf { card -> card.number.score }

    private fun countAce(cards: List<Card>) = cards.count { it.number == CardNumber.ACE }
}
