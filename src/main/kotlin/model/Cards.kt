package model

class Cards(cards: Set<Card>) {
    private val _cards = cards.toMutableSet()
    val cards: Set<Card>
        get() = _cards.toSet()
    val size: Int
        get() = _cards.size

    fun add(card: Card) {
        _cards.add(card)
    }

    fun sum(): Int {
        var sum = _cards.sumOf { it.rank.score }
        if (_cards.find { it.rank == Rank.ACE } != null && sum + ACE_BONUS_TEN <= PARTICIPANT_STANDARD_BUST_POINT) {
            sum += ACE_BONUS_TEN
        }
        return sum
    }

    fun firstCard(): Card = cards.first()

    fun isBust(): Boolean = sum() > PARTICIPANT_STANDARD_BUST_POINT
    fun isBlackJack(): Boolean = size == 2 && sum() == 21

    companion object {
        const val DEALER_STANDARD_HIT_POINT = 16
        const val PARTICIPANT_STANDARD_BUST_POINT = 21
        private const val ACE_BONUS_TEN = 10
    }
}