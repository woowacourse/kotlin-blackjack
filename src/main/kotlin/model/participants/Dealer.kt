package model.participants

class Dealer(override val hand: Hand, override val humanName: HumanName = HumanName.fromInput("딜러")) : Human(hand, humanName) {
    fun play(): Int {
        var hitCount = 0

        while (canHit()) {
            hit()
            hitCount++
        }

        return hitCount
    }

    override fun hit(): Boolean {
        if (canHit()) {
            hand.draw()
            return canHit()
        }
        return false
    }

    override fun canHit(): Boolean = getPointIncludingAce().amount <= HIT_THRESHOLD

    companion object {
        private const val HIT_THRESHOLD = 16
    }
}
