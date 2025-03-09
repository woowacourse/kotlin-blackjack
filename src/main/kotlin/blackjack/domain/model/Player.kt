package blackjack.domain.model

class Player private constructor(val name: String, override val cards: MutableList<Card> = mutableListOf()) :
    Participant() {
        constructor(name: String, cards: List<Card>) : this(name, cards.toMutableList())

        fun getScore(): Int {
            val score = this.cards.sumOf { it.rank.score }
            return score + getBonusScore(totalScore = score)
        }

        private fun getBonusScore(totalScore: Int): Int {
            if (totalScore <= MAX_BONUS_SCORE && hasAce()) return BONUS_SCORE
            return 0
        }

        private fun hasAce(): Boolean = this.cards.any { it.rank == Rank.ACE }

        fun isBust(): Boolean {
            return getScore() > BUST_THRESHOLD
        }

        companion object {
            private const val BUST_THRESHOLD = 21
            private const val MAX_BONUS_SCORE = 11
            private const val BONUS_SCORE = 10
        }
    }
