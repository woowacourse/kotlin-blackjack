package blackjack.model.betting

import blackjack.model.participant.Participants
import blackjack.model.winning.WinningResult

class BettingManager(
    private val bettingTable: BettingTable = BettingTable(),
) {
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
            val profit = bettingTable.get(name) * result.profitRate

            resultBettingTable.add(participants.dealer.name, -profit)
            resultBettingTable.add(name, profit)

            participants.dealer.receiveMoney(-profit)
            participants.players.receiveMoney(name, profit)
        }
    }
}
