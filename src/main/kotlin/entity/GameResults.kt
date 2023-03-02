package entity

import model.User

class GameResults(val value: Map<User, GameResultType>) {
    fun makeDealerGameResult(): Map<GameResultType, Int> {
        return value.asSequence()
            .groupingBy { GameResultType.values()[GameResultType.values().size - it.value.ordinal - 1] }.eachCount()
    }
}
