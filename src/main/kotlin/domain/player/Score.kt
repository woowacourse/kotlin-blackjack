package domain.player

import domain.card.Card

private const val BLACK_JACK_POINT = 21

data class Score(val cards: List<Card>) {
    val value: Int
    val type : ScoreType

    init {
        value = sumCardsPoint()
        type = ScoreType.of(value, cards.size)
    }

    private fun sumCardsPoint() :Int{
        var sum = cards.sumBy { it.point }
        cards.forEach { sum += decideSpecialPoint(sum, it) }

        return sum
    }

    private fun decideSpecialPoint(sum: Int, card: Card) :Int{
        if (sum + card.specialPoint <= BLACK_JACK_POINT) {
            return card.specialPoint
        }
        return 0
    }
}