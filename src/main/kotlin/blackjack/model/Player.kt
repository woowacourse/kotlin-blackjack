package blackjack.model

import blackjack.model.Hand.Companion.MAX_SCORE

class Player(name: String, private val betAmount: Long) : Participant(name) {
    override fun isHittable(): Boolean {
        return hand.getCardSum() < MAX_SCORE
    }

    override fun calculateBetAmount(vararg participant: Participant): Long {
        return (betAmount * judge(participant[0]).rate).toLong()
    }

    override fun judge(participant: Participant): Return {
        return when {
            this.isBusted() -> Return.LOSE
            participant.isBusted() -> Return.WIN
            this.isBlackJack() && !participant.isBlackJack() -> Return.BLACKJACK
            else -> super.judge(participant)
        }
    }
}
