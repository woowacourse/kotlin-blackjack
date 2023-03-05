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
        private const val PARTICIPANTS_SHOULD_HAVE_INITIAL_CARDS =
            "모든 참여자는 ${Participant.INIT_CARD_SIZE}장 이상의 카드를 가지고 있어야 블랙잭 결과를 생성할 수 있습니다."

        fun of(dealer: Dealer, players: Players): BlackjackResult {
            require(dealer.hasInitialCards() && players.haveInitialCards()) { PARTICIPANTS_SHOULD_HAVE_INITIAL_CARDS }
            require(dealer.shouldHit().not()) { DEALER_SHOULD_HIT_ERROR }

            val result = players.toList().associateWith { it against dealer }

            return BlackjackResult(result)
        }
    }
}
