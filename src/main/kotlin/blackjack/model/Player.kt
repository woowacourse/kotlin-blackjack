package blackjack.model

class Player(name: String) : Participant(name) {
    override fun judge(participant: Participant): GameResult {
        return when {
            this.isBusted() -> GameResult.LOSE
            participant.isBusted() -> GameResult.WIN
            else -> super.judge(participant)
        }
    }

    override fun isHitable(): Boolean {
        return getCardSum() < MAX_SCORE
    }
}
