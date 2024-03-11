package blackjack.model

class Participants(
    val dealer: Dealer,
    val players: List<Player>,
) {
    init {
        require(players.size <= MAX_PLAYER_SIZE) { PLAYER_SIZE_ERROR_MESSAGE }
    }

    fun getAllParticipants(): List<Participant> = listOf(dealer) + players

    fun getPlayerWinningState(): Map<Player, WinningResult> {
        return players.associateWith { player -> player.getWinningResult(dealer) }
    }

    fun getDealerWinningState(): Map<WinningResult, Int> {
        return players.map { player ->
            dealer.getWinningResult(player)
        }.groupingBy { it }.eachCount()
    }

    companion object {
        private const val MAX_PLAYER_SIZE = 5
        private const val PLAYER_SIZE_ERROR_MESSAGE = "플레이어의 수는 5명을 넘을 수 없습니다."
    }
}
