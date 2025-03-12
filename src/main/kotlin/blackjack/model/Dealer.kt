package blackjack.model

import blackjack.model.WinningResult.LOSE
import blackjack.model.WinningResult.PUSH
import blackjack.model.WinningResult.WIN

class Dealer(val name: String = DEALER_NAME, hand: Hand) : Participant(hand) {
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
    fun getDealerResult(player: Player): WinningResult {
        val dealerBlackjack = this.getHandSize() == 2 && this.getScore() == 21
        val playerBlackjack = player.getHandSize() == 2 && player.getScore() == 21
        return when {
            player.isBusted() -> WIN
            this.isBusted() -> LOSE
            dealerBlackjack && playerBlackjack -> PUSH
            dealerBlackjack -> WIN
            playerBlackjack -> LOSE
            this.getScore() > player.getScore() -> WIN
            this.getScore() < player.getScore() -> LOSE
            else -> PUSH
        }
    }

    fun WinningResult.reverse(): WinningResult{
        return when(this){
            LOSE -> WIN
            WIN -> LOSE
            else -> PUSH
        }
    }

    fun getUserResult( player: Player): WinningResult = getDealerResult(player).reverse()


    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_DRAW_CRITERIA = 16
        private const val INITIAL_SCORE = 0
        private const val ADDITIONAL_RESULT_COUNT = 1
    }
}
