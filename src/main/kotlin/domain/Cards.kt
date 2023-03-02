package domain

class Cards(cards: Set<Card>) {
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
        if (minSum > 21) return State.Burst(minSum)
        return State.NoBurst(minSum)
    }

    fun maxSumState(): State {
        val minSumState = minSumState()
        val aceCount = cards.count { it.cardNumber == CardNumber.ACE }
        if (aceCount == 0) return minSumState
        if (minSumState.sum + 10 > 21) return minSumState
        return State.NoBurst(minSumState.sum + 10)
    }

    sealed class State(open val sum: Int) {
        data class Burst(override val sum: Int) : State(sum)
        data class NoBurst(override val sum: Int) : State(sum)
    }


    companion object {
        private const val MINIMUM_CARDS_SIZE = 2
        private const val ERROR_CARDS_SIZE = "[ERROR] 초기 카드는 ${MINIMUM_CARDS_SIZE}장 이상이어야 합니다."
    }
}
