package domain

abstract class Participant(val name: Name, val cards: Cards) {

    fun getResult(): Cards.State {
        return cards.maxSumState()
    }

    abstract fun isMoreAddCard(): Boolean
    fun isBurst(): Cards.State {
        return cards.minSumState()
    }
}
