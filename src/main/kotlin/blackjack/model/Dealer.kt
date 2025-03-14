package blackjack.model

import blackjack.model.WinningResult.BLACKJACK
import blackjack.model.WinningResult.LOSE
import blackjack.model.WinningResult.PUSH
import blackjack.model.WinningResult.WIN

class Dealer(
    val name: String = DEALER_NAME,
    override var items: Items,
) : Participant {
    tailrec fun drawUntilFinished(cardDeck: CardDeck) {
        if (items.hand.score() > DEALER_DRAW_CRITERIA || items.hand.isBust()) return
        draw(cardDeck)
        drawUntilFinished(cardDeck)
    }

    fun getWinDrawLossResult(players: Players): Map<WinningResult, Int> {
        val result = WinningResult.entries.associateWith { INITIAL_SCORE }.toMutableMap()

        players.value.forEach { player ->
            val winningResult = getDealerResult(player)
            result[winningResult] = result.getOrDefault(winningResult, INITIAL_SCORE) + ADDITIONAL_RESULT_COUNT
        }

        return result.toMap()
    }

    override fun compareHand(other: Participant): WinningResult {
        val result = WinningResult.getResult(this, other)
        if (result == BLACKJACK) return WIN
        return result
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

    fun WinningResult.reverse(): WinningResult =
        when (this) {
            BLACKJACK -> LOSE
            LOSE -> WIN
            WIN -> LOSE
            else -> PUSH
        }

    fun getDealerResult(player: Player): WinningResult = getPlayerResult(player).reverse()

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_DRAW_CRITERIA = 16
        private const val INITIAL_SCORE = 0
        private const val ADDITIONAL_RESULT_COUNT = 1
    }
}
