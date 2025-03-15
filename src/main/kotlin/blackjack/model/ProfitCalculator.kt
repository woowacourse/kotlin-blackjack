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
        if (dealerGameStatus == BLACKJACK && playerGameStatus == BLACKJACK) return player.money.toDrawMoney()
        if (playerGameStatus == BLACKJACK) return player.money.toBlackjackMoney()
        if (playerGameStatus == BUST) return player.money.toLoseMoney()
        if (dealerGameStatus == BUST) return player.money.toWinMoney()

        val dealerFinalScore = ScoreCalculator.calculateFinalScore(dealer.cards)
        val playerFinalScore = ScoreCalculator.calculateFinalScore(player.cards)
        if (dealerFinalScore < playerFinalScore) return player.money.toWinMoney()
        if (dealerFinalScore == playerFinalScore) return player.money.toDrawMoney()
        return player.money.toLoseMoney()
    }
}
