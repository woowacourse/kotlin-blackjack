package blackjack.domain


class CardScore private constructor(val score: Int) {

    companion object {
        private val GAP_ACE: Int = CardNumber.ACE.value - 1

        fun from(cards: List<Card>): CardScore {
            var score = cards.sumOf { it.number.value }

            val aceCount = cards.count { it.number.isAce() }
            repeat(aceCount) { score = adjustAceValue(score) }
            return CardScore(score)
        }

        private fun adjustAceValue(score: Int): Int =
            if (score > Participant.TARGET_SCORE) score - GAP_ACE else score
    }
}
