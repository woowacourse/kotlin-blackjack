package domain.card

private const val BLACK_JACK_POINT = 21

data class Score(val cards: List<Card>) {
    val value: Int

    init {
        value = sumCardsPoint()
    }

    private fun sumCardsPoint() :Int{
        var sum = cards.sumBy { it.point }

        cards.forEach {
            sum += specialPoint(sum, it)
        }
        return sum
    }

    private fun specialPoint(sum: Int, card: Card) :Int{
        if (sum + card.specialPoint <= BLACK_JACK_POINT) {
            return card.specialPoint
        }
        return 0
    }
}