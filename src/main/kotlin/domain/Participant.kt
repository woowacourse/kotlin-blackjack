package domain

abstract class Participant(val name: Name, val cards: Cards) {
    abstract fun showInitCards(): List<Card>
    abstract fun isPossibleDrawCard(): Boolean
    fun getScore(): Score {
        return cards.getScore()
    }
}
