package model

class Dealer(override val hand: Hand, override val name: Name = Name.fromInput("딜러")) : Human(hand, name) {
    fun play() {
        while (hit()) ;
    }

    override fun hit(): Boolean {
        if (isPossible()) {
            hand.draw()
            return isPossible()
        }
        return false
    }

    override fun isPossible(): Boolean = getPointIncludingAce().amount <= 16
}
