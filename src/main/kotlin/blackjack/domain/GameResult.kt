package blackjack.domain

class GameResult(private val playerResult: Map<Player, ResultType>) {

    val dealer: Map<ResultType, Int> = ResultType.values().associate { type ->
        when (type) {
            ResultType.WIN -> type to playerResult.values.count { it == ResultType.LOSE }
            ResultType.TIE -> type to playerResult.values.count { it == ResultType.TIE }
            ResultType.LOSE -> type to playerResult.values.count { it == ResultType.WIN }
        }
    }

    operator fun get(player: Player): ResultType? = playerResult[player]

    companion object {
        fun of(dealer: Player, players: List<Player>): GameResult {
            val result = players.associateWith { it against dealer }

            return GameResult(result)
        }

        private infix fun Player.against(dealer: Player): ResultType {
            if (this.isBust()) return ResultType.LOSE
            if (dealer.isBust()) return ResultType.WIN

            val score = this.getScore()
            val dealerScore = dealer.getScore()
            return when {
                score > dealerScore -> ResultType.WIN
                score == dealerScore -> ResultType.TIE
                else -> ResultType.LOSE
            }
        }
    }
}
