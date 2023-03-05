package blackjack.domain

class BlackjackResult private constructor(private val player: Map<Player, ResultType>) {
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
        private const val DEALER_SHOULD_HIT_ERROR = "딜러가 히트해야 한다면 블랙잭 결과를 생성할 수 없습니다."
        private const val PLAYERS_EMPTY_ERROR = "플레이어가 없다면 블랙잭 결과를 생성할 수 없습니다."

        fun of(dealer: Dealer, players: List<Player>): BlackjackResult {
            require(dealer.shouldHit().not()) { DEALER_SHOULD_HIT_ERROR }
            require(players.isNotEmpty()) { PLAYERS_EMPTY_ERROR }

            val result = players.associateWith { it against dealer }

            return BlackjackResult(result)
        }
    }
}
