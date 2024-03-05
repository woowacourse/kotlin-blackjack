package blackjack.model

class Dealer(
    val numberOfPlayers: Int,
) {
    init {
        require(numberOfPlayers in PLAYER_NUM_RANGE) { EXCEPTION_NUMBER_OF_PLAYERS.format(numberOfPlayers) }
    }

    companion object {
        private const val MINIMUM_NUMBER_OF_PLAYERS = 2
        private const val MAXIMUM_NUMBER_OF_PLAYERS = 8
        private val PLAYER_NUM_RANGE = MINIMUM_NUMBER_OF_PLAYERS..MAXIMUM_NUMBER_OF_PLAYERS
        private const val EXCEPTION_NUMBER_OF_PLAYERS =
            "플레이어의 수로 %d를 입력했습니다. 플레이어 수는 ${MINIMUM_NUMBER_OF_PLAYERS}부터 ${MAXIMUM_NUMBER_OF_PLAYERS}까지 가능합니다."
    }
}
