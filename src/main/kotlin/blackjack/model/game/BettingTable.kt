package blackjack.model.game

import blackjack.model.participant.Money
import blackjack.model.participant.Name

class BettingTable {
    private val _table: MutableMap<Name, Money> = mutableMapOf()
    val table: BettingResult get() = BettingResult(_table)

    fun add(
        name: Name,
        money: Money,
    ) {
        _table[name] = _table[name]?.plus(money) ?: money
    }

    fun get(name: Name): Money = _table.getOrDefault(name, Money.ZERO)

    fun reset() {
        _table.clear()
    }
}
