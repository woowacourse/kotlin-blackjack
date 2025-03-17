package blackjack.model.betting

import blackjack.model.participant.Money
import blackjack.model.participant.Name
import blackjack.model.participant.Participants
import blackjack.model.participant.Players
import blackjack.model.winning.PlayersResult

class BettingMachine(
    private val bettingTable: BettingTable = BettingTable(),
) {
    fun betMoney(
        players: Players,
        setBettingMoney: (Name) -> Money,
    ) {
        players.value.forEach { player ->
            val bettingMoney = setBettingMoney(player.name)
            require(bettingMoney > Money.ZERO) {
                ("[ERROR] 베팅 금액은 0원보다 높아야 합니다.")
            }

            player.payMoney(bettingMoney)

            bettingTable.add(player.name, bettingMoney)
        }
    }

    fun result(
        playersResult: PlayersResult,
        participants: Participants,
    ): BettingResult {
        val resultBettingTable = BettingTable()
        updateTable(playersResult, resultBettingTable, participants)
        bettingTable.reset()
        return resultBettingTable.table
    }

    private fun updateTable(
        playersResult: PlayersResult,
        resultBettingTable: BettingTable,
        participants: Participants,
    ) {
        playersResult.value.forEach { (name, result) ->
            val profit = bettingTable.get(name) * result.profitRate

            resultBettingTable.add(participants.dealer.name, -profit)
            resultBettingTable.add(name, profit)

            participants.dealer.receiveMoney(-profit)
            participants.players.receiveMoney(name, profit)
        }
    }
}
