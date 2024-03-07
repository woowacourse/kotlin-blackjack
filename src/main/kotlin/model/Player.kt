package model

class Player(override val hand: Hand, override val name: Name = Name.fromInput("Player")) : Human(hand, name) {
    override fun hit(): Boolean {
        if (isPossible()) {
            hand.draw()
            return isPossible()
        }
        return false
    }

    override fun isPossible(): Boolean = getPointIncludingAce().amount < 21
}
