package blackjack.model.betting

import blackjack.model.participant.Money
import blackjack.model.participant.Name
import blackjack.model.participant.Participants
import blackjack.model.participant.Players
import blackjack.model.winning.WinningResult

class BettingManager(
    private val bettingTable: BettingTable = BettingTable(),
) {
    fun getPlayersMoney(
        players: Players,
        getBettingMoney: (Name) -> Money,
    ) {
        players.value.forEach { player ->
            val bettingMoney = getBettingMoney(player.name)
            require(bettingMoney > Money.ZERO) {
                ("[ERROR] 베팅 금액은 0원보다 높아야 합니다.")
            }
            bettingTable.add(player.name, player.payMoney(bettingMoney))
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
            val profit = bettingTable.get(name) * result.profitRate

            resultBettingTable.add(participants.dealer.name, -profit)
            resultBettingTable.add(name, profit)

            participants.dealer.receiveMoney(-profit)
            participants.players.receiveMoney(name, profit)
        }
    }
}
