package blackjack.domain

import blackjack.domain.BlackJackGame.Companion.BLACKJACK_NUMBER
class User(name: String) {
    val name = Name(name)
    val cards = Cards()
    val score: Int
        get() = if (isMaxScoreInRange) maxScore else minScore

    val minScore: Int
        get() = cards.toList().sumOf { it.value.value }

    val maxScore: Int
        get() = minScore + if (cards.containsACE() && validateAceCondition) ACE_OTHER_NUMBER_DIFF else 0

    val isNotBust: Boolean
        get() = minScore <= BLACKJACK_NUMBER

    private val validateAceCondition: Boolean
        get() = minScore + ACE_OTHER_NUMBER_DIFF <= BLACKJACK_NUMBER

    private val isMaxScoreInRange: Boolean
        get() = maxScore <= BLACKJACK_NUMBER

    fun draw(card: Card) {
        cards.add(card)
    }
    companion object {
        private const val ACE_OTHER_NUMBER_DIFF = 10
    }
}
