package blackjack.model.participant

import blackjack.model.betting.BettingResult
import blackjack.model.betting.BettingTable
import blackjack.model.card.Card
import blackjack.model.card.CardCount

class Participants(
    val dealer: Dealer,
    val players: Players,
) {
    init {
        require(players.value.find { player -> player.name == dealer.name } == null) {
            "[ERROR] 플레이어와 딜러의 이름은 중복될 수 없습니다."
        }
    }

    fun betMoney(setBettingMoney: (Name) -> Money): BettingTable {
        val bettingTable = BettingTable()
        players.value.forEach { player ->
            val bettingMoney = setBettingMoney(player.name)
            require(bettingMoney > Money.ZERO) {
                ("[ERROR] 베팅 금액은 0원보다 높아야 합니다.")
            }

            player.payMoney(bettingMoney)

            bettingTable.add(player.name, bettingMoney)
        }
        return bettingTable
    }

    fun profitResult(bettingTable: BettingTable): BettingResult {
        val resultBettingTable = BettingTable()
        updateTable(bettingTable, resultBettingTable)
        return resultBettingTable.table
    }

    private fun updateTable(
        previousBettingTable: BettingTable,
        resultBettingTable: BettingTable,
    ) {
        players.winningResult(dealer).value.forEach { (name, result) ->
            val profit = previousBettingTable.get(name) * result.profitRate

            resultBettingTable.add(dealer.name, -profit)
            resultBettingTable.add(name, profit)

            dealer.receiveMoney(-profit)
            players.receiveMoney(name, profit)
        }
    }

    companion object {
        fun create(
            dealerName: Name,
            distributeCards: (CardCount) -> List<Card>,
            getPlayerNames: () -> List<Name>,
        ): Participants {
            val dealer = Dealer.create(dealerName)
            val players = Players.from(getPlayerNames())

            dealer.receiveCards(distributeCards)
            players.value.forEach { player -> player.receiveCards(distributeCards) }

            return Participants(dealer, players)
        }
    }
}
