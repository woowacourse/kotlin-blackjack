package model

class Dealer(override val hand: Hand) : Human(hand) {
    fun play() {
        while (hit()) ;
    }

    override fun hit(): Boolean {
        if (isPossible()) {
            hand.draw()
            return true
        }
        return false
    }

    override fun isPossible(): Boolean = getPointIncludingAce().amount <= 16
}
