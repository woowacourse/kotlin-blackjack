package blackjack.model.game

import blackjack.model.participant.Money
import blackjack.model.participant.Name

class BettingManager {
    private val _bettingTable: MutableMap<Name, Money> = mutableMapOf()
    val bettingTable: Map<Name, Money> get() = _bettingTable.toMap()
    private var dealerMoney: Money = Money()

    fun bet(
        name: Name,
        money: Money,
    ) {
        _bettingTable[name] = money
        dealerMoney.plus(money)
    }

    fun result(winningResult: WinningResult) {
        winningResult.playerResults.forEach { (name, winningResult) ->
        }
    }
}
