package model

class Player(cards: Cards, name: Name) : Participant(cards, name) {
    override fun isHit(): Boolean = !isBust()
}
