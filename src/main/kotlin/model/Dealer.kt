package model

class Dealer(name: Name, cards: Cards) : Participant(name, cards) {
    override fun isHit(): Boolean {
        if (cards.sum() < 17) return true
        return false
    }
}
