package blackjack

class Player(
    val name: String,
) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards get() = _cards.toList()

    fun addCard(card: Card) = _cards.add(card)

    fun addCards(cards: List<Card>) = _cards.addAll(cards)

    fun adjustScore(): Int {
        var sumScore = sumScore()
        var countAce = countAce()
        while (countAce-- > 0) {
            if (sumScore > 21) {
                sumScore -= 10
            }
        }
        return sumScore
    }

    fun sumScore() = cards.sumOf { card -> card.number.score }

    fun isBurst() = adjustScore() > 21

    fun countAce() = _cards.count { it.number == Number.ACE }
}
