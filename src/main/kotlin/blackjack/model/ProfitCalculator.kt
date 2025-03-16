package blackjack.model

import blackjack.model.state.GameStatus.BLACKJACK
import blackjack.model.state.GameStatus.BUST
import blackjack.model.user.Dealer
import blackjack.model.user.Player

object ProfitCalculator {
    fun calculateProfit(
        dealer: Dealer,
        player: Player,
    ): Money {
        val dealerGameStatus = GameJudge.judge(dealer.cards)
        val playerGameStatus = GameJudge.judge(player.cards)
        if (dealerGameStatus == BLACKJACK && playerGameStatus == BLACKJACK) return Money.toDrawMoney()
        if (playerGameStatus == BLACKJACK) return Money.toBlackjackMoney(player.money)
        if (playerGameStatus == BUST) return Money.toLoseMoney(player.money)
        if (dealerGameStatus == BUST) return Money.toWinMoney(player.money)

        val dealerFinalScore = ScoreCalculator.calculateFinalScore(dealer.cards)
        val playerFinalScore = ScoreCalculator.calculateFinalScore(player.cards)
        if (dealerFinalScore < playerFinalScore) return Money.toWinMoney(player.money)
        if (dealerFinalScore == playerFinalScore) return Money.toDrawMoney()
        return Money.toLoseMoney(player.money)
    }
}
