package blackjack.model

class Dealer : Role() {
    val result = DealerResult()

    override fun decideMoreCard() = getCardSum() < MIN_CARD_SUM

    fun decideGameResult(player: Player): GameResult {
        return when {
            player.isBurst() -> GameResult.WIN
            isBurst() -> GameResult.LOSE
            getCardSum() > player.getCardSum() -> GameResult.WIN
            getCardSum() == player.getCardSum() -> GameResult.DRAW
            else -> GameResult.LOSE
        }
    }

    companion object {
        private const val MIN_CARD_SUM = 17
    }
}
