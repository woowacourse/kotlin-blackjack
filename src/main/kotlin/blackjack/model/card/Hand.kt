package blackjack.model.card

class Hand(
    private val _cards: MutableList<Card>,
    val stay: Boolean = false,
) {
    val cards: List<Card> get() = _cards.toList()
    val totalScore: Int get() = calculateTotalScore()

    companion object {
        const val BLACKJACK_SCORE = 21
        private const val CONVERT_ACE = 10

        // 초기 Hand 객체를 생성할 때 사용하는 팩토리 메서드
        fun initial(cards: List<Card> = listOf()): Hand = Hand(cards.toMutableList(), stay = false)
    }

    fun draw(card: Card): Hand {
        // 이미 stay 상태이거나 카드를 추가할 수 없는 경우, 현재 인스턴스 반환
        if (stay) return this
        // 새 카드를 추가한 새로운 Hand 인스턴스 반환
        return Hand(_cards.apply { add(card) }, stay)
    }

    fun decideStay(): Hand {
        return Hand(_cards, true)
    }

    private fun calculateTotalScore(): Int {
        var score = _cards.sumOf { it.denomination.score }
        var aceCount = _cards.count { it.denomination == Denomination.ACE }
        while (aceCount > 0 && score > BLACKJACK_SCORE) {
            score -= CONVERT_ACE
            aceCount--
        }
        return score
    }

    fun isBust(): Boolean = totalScore > BLACKJACK_SCORE

    fun isBlackjack(): Boolean = totalScore == BLACKJACK_SCORE && _cards.size == 2

    fun isStay(): Boolean = stay
}
