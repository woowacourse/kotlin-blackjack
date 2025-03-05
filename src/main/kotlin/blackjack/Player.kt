package blackjack

class Player : Participant {
    var totalSum: Int = 0
        private set
    val cards: MutableList<Card> = mutableListOf()

    fun addCard(card: Card) {
        check(canHit()) { "숫자의 합이 21점을 넘으면 카드를 받을 수 없습니다" }
        cards.add(card)
        totalSum = calculateTotalSum()
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

    fun canHit(): Boolean {
        return totalSum < 21
    }
}
