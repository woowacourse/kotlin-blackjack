package blackjack.domain

class BlackjackResult(private val player: Map<Player, ResultType>) {

    private val dealer: Map<ResultType, Int> = ResultType.values().associate { type ->
        when (type) {
            ResultType.WIN -> type to player.values.count { it == ResultType.LOSE }
            ResultType.TIE -> type to player.values.count { it == ResultType.TIE }
            ResultType.LOSE -> type to player.values.count { it == ResultType.WIN }
        }
    }

    fun getCountOfDealer(resultType: ResultType) = dealer[resultType]
    fun getResultOf(player: Player): ResultType? = this.player[player]

    companion object {
        fun of(dealer: Dealer, players: List<Player>): BlackjackResult {
            val result = players.associateWith { it.against(dealer) }

            return BlackjackResult(result)
        }
    }
}
