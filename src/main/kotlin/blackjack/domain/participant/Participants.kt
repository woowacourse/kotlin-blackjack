package blackjack.domain.participant

class Participants(
    val dealer: Dealer,
    val players: List<Player>,
) {
    init {
        require(players.size == MAXIMUM_PLAYERS) { ERROR_OVER_MAX_PLAYERS_MESSAGE }
    }

    companion object {
        const val ERROR_OVER_MAX_PLAYERS_MESSAGE = "[ERROR] 최대 참가자 수인 7명을 초과하였습니다."

        const val MAXIMUM_PLAYERS = 7
    }
}
