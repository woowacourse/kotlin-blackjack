package blackjack.domain

class GameResult(private val playerResult: Map<Player, ResultType>) {
    operator fun get(player: Player): ResultType? = playerResult[player]

    companion object {
        fun of(dealer: Player, players: List<Player>): GameResult {
            val result = players.associateWith { it against dealer }

            return GameResult(result)
        }

        private infix fun Player.against(dealer: Player): ResultType {
            if (this.isBusted()) return ResultType.LOSE
            if (dealer.isBusted()) return ResultType.WIN

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
