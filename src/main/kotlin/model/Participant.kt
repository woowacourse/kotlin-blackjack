package model

abstract class Participant(val cards: Cards) {
    abstract fun turn(allCards: Cards): Boolean

    fun getScore(): Int {
        return ScoreCalculator(cards).totalCardScore()
    }
}
