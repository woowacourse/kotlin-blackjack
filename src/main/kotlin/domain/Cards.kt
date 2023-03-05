package domain

import controller.BlackJackGame

class Cards(cards: List<Card>) {
    private val _cards = cards.toMutableList()
    val cards: List<Card>
        get() = _cards.toList()

    val size: Int
        get() = _cards.size

    init {
        check(cards.size >= MINIMUM_CARDS_SIZE) { ERROR_CARDS_SIZE }
    }

    fun sum(): Int {
        return cards.sumOf { it.cardNumber.value }
    }

    fun add(card: Card) {
        _cards.add(card)
    }

    fun contains(card: Card): Boolean {
        return _cards.contains(card)
    }

    fun minSumState(): State {
        val minSum = sum()
        if (minSum > BlackJackGame.BLACKJACK_NUMBER) return State.Burst(minSum)
        return State.NoBurst(minSum)
    }

    fun maxSumState(): State {
        val minSumState = minSumState()
        val aceCount = cards.count { it.isAce }
        if (aceCount == NO_COUNT) return minSumState
        if (minSumState.sum + ACE_ADDITIONAL_VALUE > BlackJackGame.BLACKJACK_NUMBER) return minSumState
        return State.NoBurst(minSumState.sum + ACE_ADDITIONAL_VALUE)
    }

    sealed class State(open val sum: Int) {
        data class Burst(override val sum: Int) : State(sum)
        data class NoBurst(override val sum: Int) : State(sum)
    }

    companion object {
        private const val MINIMUM_CARDS_SIZE = 2
        private const val ERROR_CARDS_SIZE = "[ERROR] 초기 카드는 ${MINIMUM_CARDS_SIZE}장 이상이어야 합니다."
        private const val ACE_ADDITIONAL_VALUE = 10
        private const val NO_COUNT = 0
    }
}
