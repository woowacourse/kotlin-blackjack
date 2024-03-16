package blackjack.model

class Player(name: String, betAmount: Long) : Participant(name, betAmount) {
    override fun judge(participant: Participant): Return {
        return when {
            this.isBusted() -> Return.LOSE
            participant.isBusted() -> Return.WIN
            this.isBlackJack() && !participant.isBlackJack() -> Return.BLACKJACK
            else -> super.judge(participant)
        }
    }

    override fun isHitable(): Boolean {
        return getCardSum() < MAX_SCORE
    }
}
