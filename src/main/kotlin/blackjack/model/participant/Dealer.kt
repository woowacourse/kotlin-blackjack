package blackjack.model.participant

import blackjack.model.result.GameResultType

class Dealer : Role() {
    override fun decideMoreCard() = getCardSum() < MIN_CARD_SUM

    fun decideGameResultType(player: Player): GameResultType {
        val cardSum = getCardSum()
        val playerCardSum = player.getCardSum()

        return when {
            player.isBurst() -> GameResultType.WIN
            isBurst() -> GameResultType.LOSE
            cardSum > playerCardSum -> GameResultType.WIN
            cardSum == playerCardSum -> GameResultType.DRAW
            else -> GameResultType.LOSE
        }
    }

    companion object {
        private const val MIN_CARD_SUM = 17
    }
}
