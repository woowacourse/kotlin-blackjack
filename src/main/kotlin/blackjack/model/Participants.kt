package blackjack.model

class Participants(private val dealer: Dealer, private val players: List<Player>) {
    init {
        require(players.size <= MAX_PLAYER_SIZE) { PLAYER_SIZE_ERROR_MESSAGE }
    }

    fun getDealer(): Dealer = dealer

    fun getPlayers(): List<Player> = players.toList()

    fun getAllParticipants() = listOf(dealer) + players

    fun calculateResult(): Map<Participant, ScoreRecord> {
        val result = mutableMapOf<Participant, ScoreRecord>()
        players.forEach { player ->
            calculateDealerResult(result, player)
            calculatePlayerResult(result, player)
        }
        return result.toMap()
    }

    fun calculateFinalProfits(): Map<Participant, Double> {
        val profits = mutableMapOf<Participant, Double>()

        profits[dealer] =
            players.sumOf { player ->
                dealer.calculateProfitAgainst(player)
            }

        players.forEach { player ->
            profits[player] = player.calculateProfitAgainst(dealer)
        }
        return profits
    }

    private fun calculateDealerResult(
        result: MutableMap<Participant, ScoreRecord>,
        player: Player,
    ) {
        val originalDealerGameOutcome = result.getOrDefault(dealer, DEFAULT_WINNING_STATE)
        val addDealerGameOutcome = dealer.calculateGameOutcomeAgainst(player).scoreRecord
        result[dealer] =
            ScoreRecord(
                originalDealerGameOutcome.wins + addDealerGameOutcome.wins,
                originalDealerGameOutcome.losses + addDealerGameOutcome.losses,
            )
    }

    private fun calculatePlayerResult(
        result: MutableMap<Participant, ScoreRecord>,
        player: Player,
    ) {
        result[player] = player.calculateGameOutcomeAgainst(dealer).scoreRecord
    }

    companion object {
        private val DEFAULT_WINNING_STATE = ScoreRecord(0, 0)
        private const val MAX_PLAYER_SIZE = 5
        private const val PLAYER_SIZE_ERROR_MESSAGE = "플레이어의 수는 5명을 넘을 수 없습니다."
    }
}
