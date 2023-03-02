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

    sealed class State {
        data class Burst(val sum: Int) : State()
        data class NoBurst(val sum: Int) : State()
    }


    companion object {
        private const val MINIMUM_CARDS_SIZE = 2
        private const val ERROR_CARDS_SIZE = "[ERROR] 초기 카드는 ${MINIMUM_CARDS_SIZE}장 이상이어야 합니다."
    }
}
