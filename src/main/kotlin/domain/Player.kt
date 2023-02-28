package domain

class Player(private val cards: List<Card>) {
    fun calculateCardValueSum(): Int {
        return cards.sumOf { Card.valueOf(it) }
    }
}
