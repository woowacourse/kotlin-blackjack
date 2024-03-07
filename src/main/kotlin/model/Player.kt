package model

class Player(override val hand: Hand, val name: Name = Name.fromInput("Player")) : Human(hand) {
    override fun hit(): Boolean {
        if (isPossible()) {
            hand.draw()
            return true
        }
        return false
    }

    override fun isPossible(): Boolean = getPointIncludingAce().amount < 21
}
