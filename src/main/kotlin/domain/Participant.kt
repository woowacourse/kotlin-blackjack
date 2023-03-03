package domain

abstract class Participant(val name: Name, val cards: Cards) {
    abstract fun showInitCards(): List<Card>
    abstract fun isPossibleDrawCard(): Boolean
    fun getSumStateResult(): Cards.State {
        return cards.maxSumState()
    }
    fun isBurst(): Boolean = when (cards.minSumState()) {
        is Cards.State.Burst -> true
        is Cards.State.NoBurst -> false
    }
}
