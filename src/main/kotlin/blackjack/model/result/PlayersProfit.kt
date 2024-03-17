package blackjack.model.result

import blackjack.model.role.PlayerName

data class PlayersProfit(val result: Map<PlayerName, Money>) {
    fun calculateDealerProfit(): Money = -(result.values.sumOf { it })
}

private inline fun <T> Iterable<T>.sumOf(selector: (T) -> Money): Money {
    var sum: Money = Money.ZERO
    for (element in this) {
        sum += selector(element)
    }
    return sum
}
