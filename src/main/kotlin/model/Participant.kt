package model

abstract class Participant(private val cards: Cards) {
    private val handCards: MutableList<Card>
        get() = cards.allCards

    abstract fun turn(cards: Cards): Boolean

    abstract fun isHit(): Boolean

    fun getScore(): Int {
        return ScoreCalculator(cards).calculateTotalCardScore()
    }

    fun drawCard(allCards: MutableList<Card>): Card {
        val drawnCard = allCards.take(DRAW_DEFAULT_COUNT)
        allCards.removeAll(drawnCard)
        return drawnCard.first()
    }

    fun addCard(card: Card) {
        handCards.add(card)
    }

    companion object {
        private const val DRAW_DEFAULT_COUNT = 1
    }
}
