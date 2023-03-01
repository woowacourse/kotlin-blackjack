package blackjack.domain

import blackjack.domain.BlackJackGame.Companion.BLACKJACK_NUMBER
class User(val name: String) {
    val cards = Cards()
    val score: Int
        get() = if (maxScore <= BLACKJACK_NUMBER) maxScore else minScore

    val minScore: Int
        get() = cards.toList().sumOf { it.value.value }

    val maxScore: Int
        get() {
            var score = cards.toList().sumOf { it.value.value }
            if (cards.containsACE() && score <= BLACKJACK_NUMBER - ACE_OTHER_NUMBER_DIFF) {
                score += ACE_OTHER_NUMBER_DIFF
            }
            return score
        }

    fun draw(card: Card) {
        cards.add(card)
    }
    companion object {
        private const val ACE_OTHER_NUMBER_DIFF = 10
    }
}
