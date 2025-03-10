package model

abstract class Participant(private val cards: Cards) {
    private val handCards: MutableList<Card>
        get() = cards.allCards

    abstract fun turn(drawnCard: Card): Boolean

    abstract fun canHit(): Boolean

    fun currentScore(): Int {
        return ScoreCalculator(cards).calculateTotalCardScore()
    }

    protected fun addCard(card: Card) {
        handCards.add(card)
    }
}
