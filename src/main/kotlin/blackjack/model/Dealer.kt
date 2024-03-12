package blackjack.model

class Dealer(name: String = "딜러") : Participant(name) {
    fun judge(player: Player): GameResult {
        return when {
            player.isBusted() -> GameResult.LOSE
            this.isBusted() -> GameResult.WIN
            player.isBlackJack() && this.isBlackJack() -> GameResult.DRAW
            player.isBlackJack() -> GameResult.WIN
            this.isBlackJack() -> GameResult.LOSE
            else -> compareScore(player.getCardSum(), this.getCardSum())
        }
    }

    private fun compareScore(
        playerScore: Int,
        dealerScore: Int,
    ): GameResult {
        return when {
            (playerScore > dealerScore) -> GameResult.WIN
            (playerScore < dealerScore) -> GameResult.LOSE
            else -> GameResult.DRAW
        }
    }

    override fun isHitable(): Boolean {
        return getCardSum() < DEALER_HITABLE_THRESHOLD
    }

    companion object {
        private const val DEALER_HITABLE_THRESHOLD = 17
    }
}
