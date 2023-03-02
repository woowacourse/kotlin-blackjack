class Player(val cards: Cards) {
    fun isBurst(): Boolean {
        if (cards.sum() > 21) return true
        return false
    }
}
