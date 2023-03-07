package entity

class PlayersGameResult(val value: Map<Player, GameResult>) {
    fun makeDealerGameResult(): DealerGameResult {
        return value
            .asSequence()
            .groupingBy { GameResultType.values()[GameResultType.values().size - it.value.type.ordinal - 1] }
            .eachCount()
            .let { DealerGameResult(it, value.values.sumOf { profit -> -profit.profit.value }.let { profit -> Money(profit) }) }
    }
}
