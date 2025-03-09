package blackjack.domain.participant

class Participants(
    private val value: List<Participant>,
) {
    init {
        require(players.size == players.map { it.name }.distinct().size) {
            ERROR_DUPLICATED_PLAYERS
        }

        require(players.size in MIN_PARTICIPANTS..MAX_PARTICIPANTS) {
            INVALID_PLAYERS_SIZE
        }
    }

    val dealer: Dealer get() = value.filterIsInstance<Dealer>().first()
    val players: List<Player> get() = value.filterIsInstance<Player>()

    companion object {
        private const val MIN_PARTICIPANTS = 2
        private const val MAX_PARTICIPANTS = 8

        private const val INVALID_PLAYERS_SIZE = "플레이어는 최소 2명부터 최대 8명입니다."
        private const val ERROR_DUPLICATED_PLAYERS = "플레이어 이름은 중복될 수 없습니다."
    }
}
