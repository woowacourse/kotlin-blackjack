package blackjack.domain.participant

class Participants(
    private val value: List<Participant>,
) {
    init {
        require(players.size == players.distinct().size) {
            ERROR_DUPLICATED_PLAYERS
        }
    }

    val dealer: Dealer get() = value.filterIsInstance<Dealer>().first()
    val players: List<Player> get() = value.filterIsInstance<Player>()

    companion object {
        const val ERROR_DUPLICATED_PLAYERS = "플레이어 이름은 중복될 수 없습니다."
    }
}
