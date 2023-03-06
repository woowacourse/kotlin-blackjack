package model

class Player(hand: Hand, name: Name) : Participant(hand, name) {
    override fun isHit(): Boolean = !isBust()
}
