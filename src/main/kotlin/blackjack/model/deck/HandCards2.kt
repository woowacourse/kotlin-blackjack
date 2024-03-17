package blackjack.model.deck

import blackjack.model.participant.testState.HandCardState
import blackjack.model.participant.testState.InitState

class HandCards2 {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card>
        get() = _cards.toList()

    var state: HandCardState = InitState()

    fun initCard(cards: List<Card>) {
        require(cards.size == 2) {
            "초기 카드는 두 장으로 제한됩니다."
        }
        _cards.addAll(cards)
    }

    fun playTurn(
        isHit: Boolean,
        newCards: (Int) -> List<Card>,
    ) {
        state =
            if (isHit) {
                _cards.addAll(newCards(1))
                state.nextTurn(calculateScore(), true)
            } else {
                state.nextTurn(calculateScore(), false)
            }
    }

    fun calculateScore(): Int {
        val baseScore = cards.sumOf { it.cardNumber.score }
        val hasAce = cards.any { it.cardNumber == CardNumber.ACE }
        return if (hasAce && baseScore + ANOTHER_CARD_SCORE <= BLACKJACK_NUMBER) baseScore + ANOTHER_CARD_SCORE else baseScore
    }

    companion object {
        private const val ANOTHER_CARD_SCORE = 10
        private const val BLACKJACK_NUMBER = 21
    }
}
