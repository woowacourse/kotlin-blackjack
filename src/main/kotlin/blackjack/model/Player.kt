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

    override fun judge(participant: Participant): Return {
        return when {
            this.isBusted() -> Return.LOSE
            participant.isBusted() -> Return.WIN
            this.isBlackJack() && !participant.isBlackJack() -> Return.BLACKJACK
            else -> super.judge(participant)
        }
    }

    override fun calculateBetAmount(vararg participant: Participant): Long {
        return (betAmount * judge(participant[0]).rate).toLong()
    }
}
