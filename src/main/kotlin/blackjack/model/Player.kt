package blackjack.model

import blackjack.model.Hand.Companion.MAX_SCORE

class Player(name: String, private val betAmount: Long) : Participant(name) {
    fun hitOrStay(
        dealingShoe: DealingShoe,
        askPickAgain: (String) -> Boolean,
        printCards: (Participant) -> Unit,
    ) {
        while (isHittable() && askPickAgain(name)) {
            super.pickCard(dealingShoe, 1)
            printCards(this)
        }
    }

    override fun isHittable(): Boolean {
        return hand.getCardSum() < MAX_SCORE
    }

    override fun judge(participant: Participant): GameResult {
        return when {
            this.isBusted() -> GameResult.LOSE
            participant.isBusted() -> GameResult.WIN
            this.isBlackJack() && !participant.isBlackJack() -> GameResult.BLACKJACK
            else -> super.judge(participant)
        }
    }

    fun calculateBetAmount(vararg participant: Participant): Long {
        return (betAmount * judge(participant[0]).rate).toLong()
    }
}
