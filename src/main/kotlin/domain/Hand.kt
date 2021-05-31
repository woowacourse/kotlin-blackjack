package domain

private const val BLACKJACK_SCORE: Int = 21
private const val ACE_SUBTRACT_VALUE = 10

class Hand(private val cards: MutableList<Card> = mutableListOf()) : List<Card> by cards{

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun getScore(): Int {
        var tmpSum =  cards.sumOf { it.value.score }
        val aceCount = cards.filter { it.value == Value.ACE }
            .count()

        repeat(aceCount) {
            if (tmpSum <= BLACKJACK_SCORE) {
                return tmpSum
            }
            tmpSum -= ACE_SUBTRACT_VALUE
        }
        return tmpSum
    }

    fun isBust(): Boolean {
        return getScore() > BLACKJACK_SCORE
    }

}