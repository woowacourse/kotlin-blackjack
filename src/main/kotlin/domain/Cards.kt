package domain

import controller.BlackJackGame

class Cards(cards: List<Card>) {
    private val _cards = cards.toMutableList()
    val cards: List<Card>
        get() = _cards.toList()

    val size: Int
        get() = _cards.size

    private val hasAce: Boolean
        get() = cards.count { it.isAce } > NO_COUNT

    val isBurst: Boolean
        get() = minSum() > BlackJackGame.BLACKJACK_NUMBER

    val resultSum: Int
        get() {
            if (maxSum() <= BlackJackGame.BLACKJACK_NUMBER) return maxSum()
            if (minSum() <= BlackJackGame.BLACKJACK_NUMBER) return minSum()
            return minSum()
        }

    init {
        check(cards.size >= MINIMUM_CARDS_SIZE) { ERROR_CARDS_SIZE }
    }

    fun add(card: Card) {
        _cards.add(card)
    }

    private fun minSum(): Int {
        return cards.sumOf { it.cardNumber.value }
    }

    private fun maxSum(): Int {
        if (hasAce) return minSum() + ACE_ADDITIONAL_VALUE
        return minSum()
    }

    companion object {
        private const val MINIMUM_CARDS_SIZE = 2
        private const val ERROR_CARDS_SIZE = "[ERROR] 초기 카드는 ${MINIMUM_CARDS_SIZE}장 이상이어야 합니다."
        private const val ACE_ADDITIONAL_VALUE = 10
        private const val NO_COUNT = 0
    }
}
