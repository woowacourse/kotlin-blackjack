package blackjack

abstract class Participant {
    var totalSum: Int = 0
        private set
    val cards: MutableList<Card> = mutableListOf()

    abstract val hitThreshold: Int

    fun addCard(card: Card) {
        cards.add(card)
        totalSum = calculateTotalSum()
    }

    fun isBust(): Boolean {
        return totalSum > 21
    }

    fun canHit(): Boolean {
        return totalSum < hitThreshold
    }

    private fun calculateTotalSum(): Int {
        val rawScore =
            cards.fold(0) { accumulatedScore, card ->
                accumulatedScore + card.getScore()
            }
        var editedScore = rawScore

        for (ace in cards.filter { it.rank == Rank.ACE && rawScore > 21 }) {
            editedScore -= 10
            if (editedScore <= 21) break
        }

        return editedScore
    }
}
