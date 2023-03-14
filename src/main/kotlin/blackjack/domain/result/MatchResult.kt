package blackjack.domain.result

class MatchResult {
    private val countBy: MutableMap<GameResult, Int> =
        GameResult.values().associateWith { 0 }.toMutableMap()

    fun count(gameResult: GameResult) {
        this.countBy[gameResult] =
            this.countBy[gameResult]?.plus(1) ?: throw IllegalArgumentException()
    }

    fun getResult(): GameResult =
        this.countBy.filter { it.value == 1 }.keys.first()

    fun getResults(): Map<GameResult, Int> = this.countBy.toMap()

    fun reversGameResult() {
        val winTmp = this.countBy[GameResult.WIN]
        this.countBy[GameResult.WIN] = this.countBy[GameResult.LOSE] ?: throw IllegalArgumentException()
        this.countBy[GameResult.LOSE] = winTmp ?: throw IllegalArgumentException()
    }
}
