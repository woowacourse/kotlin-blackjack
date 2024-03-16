package blackjack.model

class Dealer(name: String = "딜러") : Participant(name) {
    override fun judge(participant: Participant): Return {
        return when {
            participant.isBusted() -> Return.WIN
            this.isBusted() -> Return.LOSE
            participant.isBlackJack() && !this.isBlackJack() -> Return.LOSE_BLACKJACK
            else -> super.judge(participant)
        }
    }

    override fun isHitable(): Boolean {
        return getCardSum() < DEALER_HITABLE_THRESHOLD
    }

    companion object {
        private const val DEALER_HITABLE_THRESHOLD = 17
    }
}
