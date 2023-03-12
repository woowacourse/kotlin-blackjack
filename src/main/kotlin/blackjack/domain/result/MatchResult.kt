package blackjack.domain.result

class MatchResult(
    private val countBy: MutableMap<GameResult, Int> =
        GameResult.values().associateWith { 0 }.toMutableMap()
) {

    fun count(gameResult: GameResult) {
        countBy[gameResult] =
            countBy[gameResult]?.plus(1) ?: throw IllegalArgumentException()
    }

    fun getResult(): GameResult =
        countBy.filter { it.value == 1 }.keys.first()

    fun getResults(): Map<GameResult, Int> = countBy.toMap()
}
