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

    fun reversGameResult() {
        val winTmp = countBy[GameResult.WIN]
        countBy[GameResult.WIN] = countBy[GameResult.LOSE] ?: throw IllegalArgumentException()
        countBy[GameResult.LOSE] = winTmp ?: throw IllegalArgumentException()
    }
}
