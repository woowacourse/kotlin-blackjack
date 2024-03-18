package blackjack.state

import blackjack.model.CardNumber
import blackjack.model.Hand

abstract class Started(private val hand: Hand) : State {
    override fun hand(): Hand = hand

    override fun calculateHand(): Int {
        var total = hand.cards.sumOf { it.number.value }
        val aceCount = hand.cards.count { it.number == CardNumber.ACE }

        repeat(aceCount) {
            total += Hand.DIFF_ACE_TO_ONE

            if (total > Hand.BLACKJACK_NUMBER) {
                total -= Hand.DIFF_ACE_TO_ONE
                return@repeat
            }
        }
        return total
    }
}
