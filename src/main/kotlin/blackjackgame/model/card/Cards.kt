package blackjackgame.model.card

class Cards(private val cards: MutableList<Card>) {

    fun calculateScore(): Int {
        return cards.sumOf { it.denomination.score }
    }

    fun calculateFinalScore(): Int {
        val aceNumber = cards.count { it.denomination == Denomination.ACE }
        var totalScore = calculateScore()
        for (i in 1..aceNumber) {
            if (totalScore + 10 > 21) {
                break
            }
            totalScore += 10
        }
        return totalScore
    }

    fun add(card: Card) {
        cards.add(card)
    }

    fun addAll(cards: List<Card>) {
        this.cards.addAll(cards)
    }

    fun subList(fromIndex: Int, toIndex: Int): List<Card> {
        return this.cards.subList(fromIndex, toIndex)
    }

    fun isInitSize(): Boolean {
        return cards.size == 2
    }

    fun getCards(): List<Card> {
        return cards
    }
}
