class Dealer(cards: Cards) : Participant(cards) {
    override fun isHit(): Boolean {
        if (cards.sum() < 17) return true
        return false
    }
}
