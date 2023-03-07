package blackjack.domain

object DeckMaker {
    private val cards: MutableList<Card> = CardNumber.values()
        .flatMap { number -> CardShape.values().map { number to it } }
        .map { Card(number = it.first, shape = it.second) }
        .toMutableList()

    fun getDeck(): List<Card> = cards.shuffled()
}
