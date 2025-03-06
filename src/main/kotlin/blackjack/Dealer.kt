package blackjack

class Dealer : Participant() {
    var totalSum: Int = 0
        private set
    val cards: MutableList<Card> = mutableListOf()

    fun getCard(deck: Deck) {
        while (canHit()) {
            addCard(deck.draw())
        }
    }

    private fun addCard(card: Card) {
        cards.add(card)
        totalSum = calculateTotalSum()
    }

    fun canHit(): Boolean {
        return totalSum < 17
    }

    fun isBust(): Boolean {
        return totalSum > 21
    }

    fun getPlayerResult(player: Player): GameResultStatus {
        return when {
            totalSum > player.totalSum -> GameResultStatus.PLAYER_WIN
            player.totalSum > totalSum -> GameResultStatus.PLAYER_LOSE
            else -> GameResultStatus.DRAW
        }
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
