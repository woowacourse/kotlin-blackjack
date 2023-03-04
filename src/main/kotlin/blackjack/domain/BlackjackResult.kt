package blackjack.domain

class BlackjackResult(private val player: Map<Player, ResultType>) {

    private val dealer: Map<ResultType, Int> = ResultType.values().associateWith { type ->
        when (type) {
            ResultType.WIN -> player.values.count { it == ResultType.LOSE }
            ResultType.TIE -> player.values.count { it == ResultType.TIE }
            ResultType.LOSE -> player.values.count { it == ResultType.WIN }
        }
    }

    fun getCountOfDealer(resultType: ResultType) = dealer[resultType]
    fun getResultOf(player: Player): ResultType? = this.player[player]

    companion object {
        fun of(dealer: Dealer, players: List<Player>): BlackjackResult {
            val result = players.associateWith { it against dealer }

            return BlackjackResult(result)
        }

        private infix fun Player.against(dealer: Dealer): ResultType {
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
