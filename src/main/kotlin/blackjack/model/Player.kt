package blackjack.model

class Player(
    val name: String,
    cards: Set<Card> = emptySet(),
    val onInputDecision: () -> String,
) {
    private val _cards: MutableSet<Card> = cards.toMutableSet()
    val cards: Set<Card>
        get() = _cards

    fun getCard(generateCard: () -> Card): PickingState {
        return when (onInputDecision()) {
            HIT -> {
                _cards.add(generateCard())
                checkBurst()
            }

            STAY -> PickingState.STOP

            else -> throw IllegalArgumentException(EXCEPTION_PLAYER_INPUT)
        }
    }

    private fun checkBurst(): PickingState {
        return if (cards.sumOf { it.value } > MAXIMUM_CARD_TOTAL) PickingState.STOP else PickingState.SUCCESS
    }

    companion object {
        private const val MAXIMUM_CARD_TOTAL = 21
        private const val HIT = "y"
        private const val STAY = "n"
        private const val EXCEPTION_PLAYER_INPUT = "y나 n을 입력해야 합니다."
    }
}
