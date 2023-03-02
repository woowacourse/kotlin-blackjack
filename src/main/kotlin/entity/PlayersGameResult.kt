package entity

class PlayersGameResult(val value: Map<Player, GameResultType>) {
    fun makeDealerGameResult(): DealerGameResult {
        return value
            .asSequence()
            .groupingBy { GameResultType.values()[GameResultType.values().size - it.value.ordinal - 1] }
            .eachCount()
            .let { DealerGameResult(it) }
    }
}
