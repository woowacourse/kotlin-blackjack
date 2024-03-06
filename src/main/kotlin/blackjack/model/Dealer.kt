package blackjack.model

class Dealer(
    val numberOfPlayers: Int,
    cards: Set<Card> = emptySet(),
) {
    private val _cards: MutableSet<Card> = cards.toMutableSet()
    val cards: Set<Card>
        get() = _cards

    init {
        require(numberOfPlayers in PLAYER_NUM_RANGE) {
            EXCEPTION_NUMBER_OF_PLAYERS.format(numberOfPlayers)
        }
    }

    fun getResult(): PlayerStat {
        return PlayerStat("딜러", cards.sumOf { it.value })
    }

    fun drawCard(generateCard: () -> Card): PickingState {
        if (isDrawAvailable()) {
            _cards.add(generateCard())
            return PickingState.CONTINUE
        } else {
            return PickingState.STOP
        }
    }

    private fun isDrawAvailable(): Boolean {
        val total = cards.sumOf { card -> card.value }
        return total <= MAXIMUM_DRAW_THRESHOLD
    }

    companion object {
        private const val MAXIMUM_DRAW_THRESHOLD = 16
        private const val MINIMUM_NUMBER_OF_PLAYERS = 2
        private const val MAXIMUM_NUMBER_OF_PLAYERS = 8
        private val PLAYER_NUM_RANGE = MINIMUM_NUMBER_OF_PLAYERS..MAXIMUM_NUMBER_OF_PLAYERS
        private const val EXCEPTION_NUMBER_OF_PLAYERS =
            "플레이어의 수로 %d를 입력했습니다. 플레이어 수는 ${MINIMUM_NUMBER_OF_PLAYERS}부터 ${MAXIMUM_NUMBER_OF_PLAYERS}까지 가능합니다."
    }
}
