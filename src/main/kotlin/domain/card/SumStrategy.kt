package domain.card

fun interface SumStrategy {
    fun getSum(handOfCards: HandOfCards): Int

    fun countAce(handOfCards: HandOfCards) = handOfCards
}
