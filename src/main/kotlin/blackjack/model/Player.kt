package blackjack.model

import blackjack.model.Hand.Companion.MAX_SCORE

class Player(name: String, betAmount: Long) : Participant(name, betAmount) {
    override fun judge(participant: Participant): Return {
        return when {
            this.isBusted() -> Return.LOSE
            participant.isBusted() -> Return.WIN
            this.isBlackJack() && !participant.isBlackJack() -> Return.BLACKJACK
            else -> super.judge(participant)
        }
    }

    override fun isHittable(): Boolean {
        return hand.getCardSum() < MAX_SCORE
    }
}
