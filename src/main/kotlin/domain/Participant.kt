package domain

abstract class Participant(val name: Name, val cards: Cards) {
    abstract fun showInitCards(): List<Card>
    abstract fun isMoreAddCard(): Boolean
    fun getResult(): Cards.State {
        return cards.maxSumState()
    }
    fun isBurst(): Cards.State {
        return cards.minSumState()
    }
}
