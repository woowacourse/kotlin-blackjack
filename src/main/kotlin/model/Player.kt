package model

class Player(name: Name, cards: Cards) : Participant(name, cards) {
    override fun isHit(): Boolean = !isBust()
}
