package domain

class Player(val card: List<Card>) {
    fun calculateCardValueSum(): Int {
        return card.sumOf { Card.valueOf(it) }
    }
}
