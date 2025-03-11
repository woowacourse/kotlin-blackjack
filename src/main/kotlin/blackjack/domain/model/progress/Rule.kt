package blackjack.domain.model.progress

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Number.ACE
import blackjack.domain.model.participant.CardStatus

class Rule {
    companion object {
        // todo 제거예졍
        fun calculateBestCardValueInRule(cards: Collection<Card>): Int {
            val cardNumbers = cards.map { it.number }
            val minimumSum = calculateCardValueMinimumSum(cards)

            if (ACE in cardNumbers && (minimumSum + ACE_VALUE_GAP) <= CardStatus.BLACKJACK_NUMBER) {
                return minimumSum + ACE_VALUE_GAP
            }
            return minimumSum
        }

        private fun calculateCardValueMinimumSum(cards: Collection<Card>): Int {
            val cardValues = cards.map { it.getMinimumValue() }
            return cardValues.sum()
        }

        private const val ACE_VALUE_GAP = 10
    }
}
