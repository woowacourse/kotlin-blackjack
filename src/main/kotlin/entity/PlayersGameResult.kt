package entity

class PlayersGameResult(val value: Map<Player, GameResult>) {
    fun makeDealerGameResult(): DealerGameResult {
        return value
            .asSequence()
            .groupingBy { it.value.type.opposite() }
            .eachCount()
            .let { DealerGameResult(it, value.values.sumOf { profit -> -profit.profit.value }.let { profit -> Money(profit) }) }
    }
}
