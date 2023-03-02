package model

class Dealer(name: Name = Name(DEALER), cards: Cards) : Participant(name, cards) {
    override fun isHit(): Boolean {
        if (cards.sum() < 17) return true
        return false
    }

    companion object {
        private const val DEALER = "딜러"
    }
}
