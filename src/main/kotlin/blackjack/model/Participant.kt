package blackjack.model

abstract class Participant(
    private val cardDeck: CardDeck,
) {
    val hand = Hand()
    var score: Int = 0
        private set

    init {
        draw(2)
    }

    fun draw(count: Int) {
        hand.addAll(cardDeck.draw(count))
    }
}
