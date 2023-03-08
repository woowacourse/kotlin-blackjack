package entity

class PlayersGameResult(val value: Map<Player, GameResult>) {
    fun makeDealerGameResult(): DealerGameResult {
        return value
            .asSequence()
            .groupingBy { it.value.type.opposite() }
            .eachCount()
            .let { DealerGameResult(it, sumOfOppositeMoney()) }
    }

    private fun sumOfOppositeMoney(): Money {
        return Money(value.values.sumOf { profit -> -profit.profit.value })
    }
}
