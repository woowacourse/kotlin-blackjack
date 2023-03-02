package model

class Player(val name: Name, cards: Cards) : Participant(cards) {
    override fun isHit(): Boolean = !isBurst()
}
