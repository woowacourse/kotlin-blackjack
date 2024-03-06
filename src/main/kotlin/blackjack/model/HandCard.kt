package blackjack.model

class HandCard(private val cards: List<Card>) {

    fun totalSumCards(): Int {
        return cards.sumOf { card -> card.getScore() }
    }
}
