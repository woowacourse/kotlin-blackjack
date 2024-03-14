package blackjack.model

class Dealer(name: String = "딜러") : Participant(name) {
    override fun judge(participant: Participant): GameResult {
        return when {
            participant.isBusted() -> GameResult.WIN
            this.isBusted() -> GameResult.LOSE
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
