package blackjack.domain

class Cards(private val cards: MutableList<Card>) {

    init {
        checkCardInitSize()
    }

    private fun checkCardInitSize() {
        require(cards.size == CARDS_INITIAL_SIZE) { ERROR_CARDS_INITIAL_SIZE }
    }

    companion object {
        const val CARDS_INITIAL_SIZE = 2
        const val ERROR_CARDS_INITIAL_SIZE = "처음 받는 카드는 두 장이어야 합니다"
    }
}
