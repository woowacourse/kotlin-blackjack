package blackjack.domain

class CardBunch(private val cards: Set<Card>) {
    init {
        validateSize()
    }

    private fun validateSize() {
        require(cards.size == 2) { INITIAL_SIZE_ERROR }
    }

    companion object {
        private const val INITIAL_SIZE_ERROR = "초기 카드는 2장이 배정되어야 합니다."
    }
}
