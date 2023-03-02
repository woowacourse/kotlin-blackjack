package model

class Dealer(cards: Cards, name: Name = Name(DEALER)) : Participant(cards, name) {
    override fun isHit(): Boolean {
        if (cards.sum() < 17) return true
        return false
    }

    companion object {
        private const val DEALER = "딜러"
    }
}
