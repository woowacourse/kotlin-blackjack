package blackjack.domain

class BlackjackResult private constructor(private val playersRevenue: Map<Player, Int>) {
    val dealerRevenue: Int = -playersRevenue.values.sum()

    fun getRevenueOf(player: Player): Int? = playersRevenue[player]

    companion object {
        private const val DEALER_SHOULD_HIT_ERROR = "딜러가 히트해야 한다면 블랙잭 결과를 생성할 수 없습니다."
        private const val PARTICIPANTS_SHOULD_HAVE_INITIAL_CARDS =
            "모든 참여자는 ${Participant.INIT_CARD_SIZE}장 이상의 카드를 가지고 있어야 블랙잭 결과를 생성할 수 있습니다."

        fun of(dealer: Dealer, playersBetAmount: Map<Player, Money>): BlackjackResult {
            val players = Players(playersBetAmount.keys)
            require(dealer.hasInitialCards() && players.haveInitialCards()) { PARTICIPANTS_SHOULD_HAVE_INITIAL_CARDS }
            require(dealer.shouldHit().not()) { DEALER_SHOULD_HIT_ERROR }

            val result = createResult(dealer, playersBetAmount)

            return BlackjackResult(result)
        }

        private fun createResult(dealer: Dealer, playersBetAmount: Map<Player, Money>): Map<Player, Int> {
            val players = Players(playersBetAmount.keys)

            return players.associateWith { it against dealer }.map { (player, result) ->
                val playerBetAmount = playersBetAmount[player]?.toInt() ?: 0
                when (result) {
                    ResultType.WIN -> player to if (player.isBlackjack()) playerBetAmount + (playerBetAmount / 2) else playerBetAmount
                    ResultType.TIE -> player to 0
                    ResultType.LOSE -> player to -playerBetAmount
                }
            }.toMap()
        }
    }
}
