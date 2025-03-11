package blackjack.model

import blackjack.model.WinningResult.LOSE
import blackjack.model.WinningResult.PUSH
import blackjack.model.WinningResult.WIN

object CalculateResult {
    fun getUserResult(dealer: Dealer, player: Player): WinningResult {
        val dealerBlackjack = dealer.getHandSize() == 2 && dealer.getScore() == 21
        val playerBlackjack = player.getHandSize() == 2 && player.getScore() == 21
        val dealerBusted = dealer.isBusted()
        val playerBusted = player.isBusted()
        return when {
            playerBusted -> LOSE
            dealerBusted -> WIN
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

    fun getDealerResult(dealer: Dealer, player: Player): WinningResult = getUserResult(dealer, player).reverse()
}
