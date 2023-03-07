package blackjack.domain

import blackjack.domain.card.Cards

enum class Result {
    WIN, LOSE, DRAW;

    companion object {
        fun valueOf(dealerSum: Int, participantSum: Int): Result {
            when {
                ((dealerSum > Cards.MAX_SUM_NUMBER) and (participantSum > Cards.MAX_SUM_NUMBER)) -> return DRAW
                (participantSum > Cards.MAX_SUM_NUMBER) -> return LOSE
                (dealerSum > Cards.MAX_SUM_NUMBER) -> return WIN
                (dealerSum > participantSum) -> return LOSE
                (dealerSum == participantSum) -> return DRAW
            }
            return WIN
        }

        fun reverse(result: Result) = when (result) {
            WIN -> LOSE
            LOSE -> WIN
            else -> DRAW
        }
    }
}
