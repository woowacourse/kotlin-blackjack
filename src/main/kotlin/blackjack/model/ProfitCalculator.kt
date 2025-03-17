package blackjack.model

import blackjack.model.state.GameStatus.BLACKJACK
import blackjack.model.state.GameStatus.BUST
import blackjack.model.user.Dealer
import blackjack.model.user.Player

object ProfitCalculator {
    fun calculateProfit(
        dealer: Dealer,
        player: Player,
    ): Money = getMoneyByGameStatusOrNull(dealer, player) ?: getMoneyByCompareToScore(dealer, player)

    private fun getMoneyByGameStatusOrNull(
        dealer: Dealer,
        player: Player,
    ): Money? {
        val dealerGameStatus = GameJudge.judge(dealer.hand)
        val playerGameStatus = GameJudge.judge(player.hand)
        if (dealerGameStatus == BLACKJACK && playerGameStatus == BLACKJACK) return Money.toDrawMoney()
        if (playerGameStatus == BLACKJACK) return Money.toBlackjackMoney(player.money)
        if (playerGameStatus == BUST) return Money.toLoseMoney(player.money)
        if (dealerGameStatus == BUST) return Money.toWinMoney(player.money)
        return null
    }

    private fun getMoneyByCompareToScore(
        dealer: Dealer,
        player: Player,
    ): Money {
        val dealerScore = dealer.hand.score
        val playerScore = player.hand.score
        if (dealerScore < playerScore) return Money.toWinMoney(player.money)
        if (dealerScore == playerScore) return Money.toDrawMoney()
        return Money.toLoseMoney(player.money)
    }
}
