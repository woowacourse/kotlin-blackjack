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
            result[dealer] = dealer.calculateWinningStateAgainst(player)
            result[player] = player.calculateWinningStateAgainst(dealer)
        }
        return result.toMap()
    }

    companion object {
        private const val MAX_PLAYER_SIZE = 5
        private const val PLAYER_SIZE_ERROR_MESSAGE = "플레이어의 수는 5명을 넘을 수 없습니다."
    }
}
