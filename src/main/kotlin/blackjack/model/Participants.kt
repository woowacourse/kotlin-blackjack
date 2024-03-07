package blackjack.model

data class Participants(
    val dealer: Dealer,
    val players: List<Player>,
) {
    init {
        require(players.size <= 5) { PLAYER_SIZE_ERROR_MESSAGE }
    }

    fun getAllParticipants(): List<Participant> = listOf(dealer) + players

    companion object {
        private const val MAX_PLAYER_SIZE = 5
        private const val PLAYER_SIZE_ERROR_MESSAGE = "플레이어의 수는 5명을 넘을 수 없습니다."
    }
}
