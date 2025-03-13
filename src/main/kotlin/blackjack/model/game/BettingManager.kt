package blackjack.model.game

import blackjack.model.participant.Money
import blackjack.model.participant.Name
import blackjack.model.participant.Participants
import blackjack.model.participant.Players

class BettingManager {
    private val bettingTable: BettingTable = BettingTable()

    fun getPlayersMoney(
        players: Players,
        getBettingMoney: (Name) -> Money,
    ) {
        players.value.forEach { player ->
            val bettingMoney = getBettingMoney(player.name)

            player.payMoney(bettingMoney)
            bettingTable.add(player.name, bettingMoney)
        }
    }

    fun result(
        winningResult: WinningResult,
        participants: Participants,
    ): BettingResult {
        val resultBettingTable = BettingTable()
        updateTable(winningResult, resultBettingTable, participants)
        bettingTable.reset()
        return resultBettingTable.table
    }

    private fun updateTable(
        winningResult: WinningResult,
        resultBettingTable: BettingTable,
        participants: Participants,
    ) {
        winningResult.playerResults.forEach { (name, result) ->
            val profit = bettingTable.get(name).multiply(result.profitRate)
            resultBettingTable.add(participants.dealer.name, profit.reverse())
            resultBettingTable.add(name, profit)

            participants.dealer.recieveMoney(profit.reverse())
            participants.players.receiveMoney(name, profit)
        }
    }
}
