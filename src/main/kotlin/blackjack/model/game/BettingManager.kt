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
    ): Map<Name, Money> {
        val profitResult = BettingTable()

        profitResult.add(dealer.name, Money.ZERO)
        winningResult.playerResults.forEach { (name, result) ->
            profitResult.add(name, bettingTable.get(name).multiply(result.profitRate))
        }

        return profitResult.playersTable
    }
}
