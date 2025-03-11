package model

abstract class Participant(private val cards: Cards) {
    private val handCards: MutableList<Card>
        get() = cards.allCards

    val currentScore: Int
        get() = ScoreCalculator(cards).calculateTotalCardScore()

    abstract fun turn(drawnCard: Card): Boolean

    abstract fun canHit(): Boolean


    protected fun addCard(card: Card) {
        handCards.add(card)
    }
}
