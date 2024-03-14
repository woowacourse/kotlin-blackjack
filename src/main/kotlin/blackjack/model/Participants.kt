package blackjack.model

class Participants(private val dealer: Dealer, private val players: List<Player>) {
    init {
        require(players.size <= MAX_PLAYER_SIZE) { PLAYER_SIZE_ERROR_MESSAGE }
    }

    fun getDealer(): Dealer = dealer

    fun getPlayers(): List<Player> = players.toList()

    fun getAllParticipants() = listOf(dealer) + players

    fun calculateResult(): Map<Participant, WinningState> {
        val result = mutableMapOf<Participant, WinningState>()
        players.forEach { player ->
            calculateDealerResult(result, player)
            calculatePlayerResult(result, player)
        }
        return result.toMap()
    }

    fun calculateFinalProfits(): Map<Participant, Double> {
        val profits = mutableMapOf<Participant, Double>()

        profits[dealer] =
            -(
                players.sumOf { player ->
                    player.calculateProfitAgainst(dealer)
                }
            )

        players.forEach { player ->
            profits[player] = player.calculateProfitAgainst(dealer)
        }
        return profits
    }

    private fun calculateDealerResult(
        result: MutableMap<Participant, WinningState>,
        player: Player,
    ) {
        val originalDealerWinningState = result.getOrDefault(dealer, WinningState(0, 0))
        val addDealerWinningState = dealer.calculateWinningStateAgainst(player)
        result[dealer] =
            WinningState(
                originalDealerWinningState.wins + addDealerWinningState.wins,
                originalDealerWinningState.losses + addDealerWinningState.losses,
            )
    }

    private fun calculatePlayerResult(
        result: MutableMap<Participant, WinningState>,
        player: Player,
    ) {
        result[player] = player.calculateWinningStateAgainst(dealer)
    }

    companion object {
        private const val MAX_PLAYER_SIZE = 5
        private const val PLAYER_SIZE_ERROR_MESSAGE = "플레이어의 수는 5명을 넘을 수 없습니다."
    }
}
