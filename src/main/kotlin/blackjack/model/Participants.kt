package blackjack.model

data class Participants(
    val dealer: Dealer,
    val players: List<Player>,
) {
    init {
        require(players.size + DEALER_SIZE <= MAX_PARTICIPANTS_SIZE) {
            ERROR_MAX_PARTICIPANTS_SIZE
        }
        require(players.isNotEmpty()) {
            ERROR_NOT_EXIST_PARTICIPANT
        }
        require(players.size >= MIN_PLAYER_SIZE) {
            ERROR_NOT_EXIST_PLAYERS
        }
    }

    fun getAlivePlayers(): List<Participant> {
        return players.filter { player ->
            player.isHitState()
        }
    }

    companion object {
        private const val MAX_PARTICIPANTS_SIZE: Int = 8
        private const val DEALER_SIZE: Int = 1
        private const val MIN_PLAYER_SIZE: Int = 1
        private const val ERROR_MAX_PARTICIPANTS_SIZE = "게임 참가자의 수는 ${MAX_PARTICIPANTS_SIZE}을 초과할 수 없습니다."
        private const val ERROR_NOT_EXIST_PARTICIPANT = "참가자가 없습니다."
        private const val ERROR_NOT_EXIST_PLAYERS = "플레이어가 없습니다."
    }
}
