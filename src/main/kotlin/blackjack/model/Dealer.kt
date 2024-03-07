package blackjack.model

class Dealer(handCards: HandCards) {
    private val _handCards: MutableList<Card> = handCards.cards.toMutableList()
    val handCards: HandCards get() = HandCards(_handCards.toList())

    // 카드를 받는 행동

    fun canHit(): Boolean {
        val sum = handCards.sumOptimizedOrNull() ?: return false
        return sum < HIT_CONDITION
    }

    fun hit(card: Card) {
        if (canHit()) {
            _handCards.add(card)
        }
    }

    companion object {
        const val HIT_CONDITION = 17
    }
}
// 상태 : 손패, (승 : 0, 패 : 0, 무슨부 : 0 )
// 행동 : isWin(플레이어와 승 패를 결정함), 기준(17점)으로 Stay, Hit 결정
