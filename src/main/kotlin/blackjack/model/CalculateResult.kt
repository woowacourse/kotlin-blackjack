package blackjack.model

import blackjack.model.WinningResult.LOSE
import blackjack.model.WinningResult.PUSH
import blackjack.model.WinningResult.WIN

object CalculateResult {
    fun getDealerResult(dealer: Dealer, player: Player): WinningResult {
        val dealerBlackjack = dealer.getHandSize() == 2 && dealer.getScore() == 21
        val playerBlackjack = player.getHandSize() == 2 && player.getScore() == 21
        return when {
            player.isBusted() -> WIN
            dealer.isBusted() -> LOSE
            dealerBlackjack && playerBlackjack -> PUSH
            dealerBlackjack -> WIN
            playerBlackjack -> LOSE
            dealer.getScore() > player.getScore() -> WIN
            dealer.getScore() < player.getScore() -> LOSE
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

    fun getUserResult(dealer: Dealer, player: Player): WinningResult = getDealerResult(dealer, player).reverse()
}
