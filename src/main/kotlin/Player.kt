class Player(cards: Cards) : Participant(cards) {
    override fun isHit(): Boolean {
        if (isBurst()) {
            return false
        }
        return true
    }
}
