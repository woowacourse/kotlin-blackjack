package blackjack.model.participant

import blackjack.model.result.DealerResult
import blackjack.model.result.GameResultStorage
import blackjack.model.result.GameResultType
import blackjack.model.result.PlayersResult

class Dealer : Role() {
    override fun decideMoreCard() = getCardSum() < MIN_CARD_SUM

    fun calculateGameResult(players: Players): GameResultStorage {
        val playersResult = PlayersResult()
        var dealerProfit = 0.0
        val profit = Profit()
        players.playerGroup.forEach { player ->
            val gameResultType = decideGameResultType(player)
            val playerProfit = profit.calculateProfit(player.battingAmount, gameResultType)
            playersResult.add(player.name, playerProfit)
            dealerProfit -= playerProfit.profit
        }
        return GameResultStorage(DealerResult(Profit(dealerProfit)), playersResult)
    }

    private fun decideGameResultType(player: Player): GameResultType {
        return when {
            player.isBurst() -> GameResultType.LOSE
            isBurst() -> GameResultType.WIN
            getCardSum() > player.getCardSum() -> GameResultType.LOSE
            getCardSum() == player.getCardSum() -> GameResultType.DRAW
            player.isBlackjack() -> GameResultType.BLACKJACK
            else -> GameResultType.WIN
        }
    }

    companion object {
        private const val MIN_CARD_SUM = 17
    }
}
