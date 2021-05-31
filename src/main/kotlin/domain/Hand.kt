package domain

const val BLACKJACK_SCORE: Int = 21
const val ACE_SUBTRACT_VALUE = 10

class Hand(val cards: MutableList<Card> = mutableListOf()) {

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

}