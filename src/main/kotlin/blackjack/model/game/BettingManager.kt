package blackjack.model.game

import blackjack.model.participant.Dealer
import blackjack.model.participant.Money
import blackjack.model.participant.Name
import blackjack.model.participant.Participants
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

    fun result(
        participants: Participants,
        winningResult: WinningResult,
    ): BettingResult {
        val bettingResult = BettingTable()

        updateTable(bettingResult, participants.dealer, winningResult)

        bettingTable.reset()
        distributeMoney(participants, bettingResult.table)

        return bettingResult.table
    }

    private fun updateTable(
        profitResult: BettingTable,
        dealer: Dealer,
        winningResult: WinningResult,
    ) {
        profitResult.add(dealer.name, Money.ZERO)

        winningResult.playerResults.forEach { (name, result) ->
            val profit = bettingTable.get(name).multiply(result.profitRate)
            profitResult.add(name, profit)
            profitResult.add(dealer.name, profit.minus(profit))
        }
    }

    private fun distributeMoney(
        participants: Participants,
        bettingResult: BettingResult,
    ) {
        bettingResult.value.forEach { (name, money) ->
            when (name == participants.dealer.name) {
                true -> participants.dealer.recieveMoney(money)
                false -> participants.players.receiveMoney(name, money)
            }
        }
    }
}
