package blackjack.model.game

import blackjack.model.participant.Money
import blackjack.model.participant.Name

class BettingTable {
    private val _playersTable: MutableMap<Name, Money> = mutableMapOf()
    val playersTable: BettingResult get() = BettingResult(_playersTable)

    fun add(
        name: Name,
        money: Money,
    ) {
        _playersTable[name] = _playersTable.getOrDefault(name, Money.ZERO).plus(money)
    }

    fun get(name: Name): Money = _playersTable.getOrDefault(name, Money.ZERO)

    fun reset() {
        _playersTable.clear()
    }
}
