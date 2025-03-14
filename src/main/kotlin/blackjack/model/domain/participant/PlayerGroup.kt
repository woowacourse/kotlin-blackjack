package blackjack.model.domain.participant

class PlayerGroup(
    val players: List<Player>,
    val dealer: Dealer,
) {
    init {
        require(players.size <= MAX_PLAYERS_SIZE) { INVALID_PLAYERS_SIZE }
        require(players.toSet().size == players.size) { INVALID_PLAYERS_DUPLICATED }
    }

    companion object {
        private const val MAX_PLAYERS_SIZE: Int = 7
        private const val INVALID_PLAYERS_SIZE: String = "[ERROR] 참여자의 최대 인원은 8명입니다."
        private const val INVALID_PLAYERS_DUPLICATED: String = "[ERROR] 참여자의 이름은 중복될 수 없습니다."
    }
}
