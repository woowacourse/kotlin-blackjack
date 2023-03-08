package blackjack.domain.result

import blackjack.domain.card.CardNumber
import blackjack.domain.card.Cards

class Score {
    companion object {

        const val MAX_SUM_NUMBER = 21

        fun getParticipantResult(dealerCards: Cards, participantCards: Cards): Result {
            var dealerCardsSum: Int = dealerCards.sum()
            var participantCardsSum: Int = participantCards.sum()

            if (dealerCards.size == 2 && dealerCards.containsCardNumber(CardNumber.ONE)) dealerCardsSum += 10
            if (participantCards.size == 2 && participantCards.containsCardNumber(CardNumber.ONE)) participantCardsSum += 10

            return when {
                participantCardsSum > MAX_SUM_NUMBER -> Result.LOSE
                dealerCardsSum > MAX_SUM_NUMBER -> Result.WIN
                dealerCardsSum > participantCardsSum -> Result.LOSE
                dealerCardsSum == participantCardsSum -> Result.DRAW
                else -> Result.WIN
            }
        }

        fun reversResult(result: Result): Result = when (result) {
            Result.WIN -> Result.LOSE
            Result.LOSE -> Result.WIN
            else -> Result.DRAW
        }
    }
}
