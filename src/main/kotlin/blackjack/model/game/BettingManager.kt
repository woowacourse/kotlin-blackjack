package blackjack.model.game

import blackjack.model.participant.Dealer
import blackjack.model.participant.Money
import blackjack.model.participant.Name
import blackjack.model.participant.Players

class BettingManager {
    private val bettingTable: BettingTable = BettingTable()

    fun getPlayersMoney(
        players: Players,
        getMoney: (Name) -> Money,
    ) {
        players.value.forEach { player ->
            val money = getMoney(player.name)

            player.payMoney(money)
            bettingTable.add(player.name, money)
        }
    }

    fun end(
        dealer: Dealer,
        winningResult: WinningResult,
    ): BettingResult {
        val profitResult = BettingTable()

        profitResult.add(dealer.name, Money.ZERO)
        winningResult.playerResults.forEach { (name, result) ->
            val profit = bettingTable.get(name).multiply(result.profitRate)
            profitResult.add(name, profit)
            profitResult.add(dealer.name, profit.minus(profit))
        }

        bettingTable.reset()

        return profitResult.playersTable
    }
}
