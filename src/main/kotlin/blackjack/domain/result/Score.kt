package blackjack.domain.result

import blackjack.domain.card.CardNumber
import blackjack.domain.card.Cards

class Score {
    companion object {

        const val MAX_SUM_NUMBER = 21

        fun getParticipantResult(dealerCards: Cards, participantCards: Cards): Result {
            var dealerCardsSum: Int = dealerCards.sum()
            var participantCardsSum: Int = participantCards.sum()

            dealerCardsSum += bonus(dealerCards)
            participantCardsSum += bonus(participantCards)

            return when {
                participantCardsSum > MAX_SUM_NUMBER -> Result.LOSE
                dealerCardsSum > MAX_SUM_NUMBER -> Result.WIN
                dealerCardsSum > participantCardsSum -> Result.LOSE
                dealerCardsSum == participantCardsSum -> Result.DRAW
                else -> Result.WIN
            }
        }

        private fun bonus(cards: Cards): Int = if (cards.size == 2 && cards.containsCardNumber(CardNumber.ONE)) 10 else 0

        fun reversResult(result: Result): Result = when (result) {
            Result.WIN -> Result.LOSE
            Result.LOSE -> Result.WIN
            else -> Result.DRAW
        }
    }
}
