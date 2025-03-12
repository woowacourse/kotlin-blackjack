package blackjack.model

import blackjack.model.WinningResult.LOSE
import blackjack.model.WinningResult.PUSH
import blackjack.model.WinningResult.WIN
import blackjack.model.WinningResult.BLACKJACK

class Dealer(val name: String = DEALER_NAME, override val hand: Hand) : Participant {

    override var money = Amount(0)
    var drawCount = 0

    fun drawUntilFinished(cardDeck: CardDeck) {
        while (hand.score() <= DEALER_DRAW_CRITERIA && !hand.isBust()) {
            drawCount++
            draw(cardDeck)
        }
    }

    fun getAdditionalDrawCount(): Int {
        return drawCount
    }

    fun getWinDrawLossResult(players: Players): Map<WinningResult, Int> {
        val result = WinningResult.entries.associateWith { INITIAL_SCORE }.toMutableMap()

        players.value.forEach { player ->
            val winningResult = getDealerResult(player)
            result[winningResult] = result.getOrDefault(winningResult, INITIAL_SCORE) + ADDITIONAL_RESULT_COUNT
        }

        return result.toMap()
    }

    fun getPlayerResult(player: Player): WinningResult {
        val dealerBlackjack = this.getHandSize() == 2 && this.getScore() == 21
        val playerBlackjack = player.getHandSize() == 2 && player.getScore() == 21
        return when {
            dealerBlackjack && playerBlackjack -> PUSH
            playerBlackjack -> BLACKJACK
            dealerBlackjack -> LOSE
            player.isBusted() -> LOSE
            this.isBusted() -> WIN
            this.getScore() > player.getScore() -> LOSE
            this.getScore() < player.getScore() -> WIN
            else -> PUSH
        }
    }

    fun WinningResult.reverse(): WinningResult {
        return when (this) {
            BLACKJACK -> LOSE
            LOSE -> WIN
            WIN -> LOSE
            else -> PUSH
        }
    }

    fun getDealerResult(player: Player): WinningResult = getPlayerResult(player).reverse()


    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_DRAW_CRITERIA = 16
        private const val INITIAL_SCORE = 0
        private const val ADDITIONAL_RESULT_COUNT = 1
    }
}
