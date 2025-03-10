package blackjack.model

abstract class Participant(firstCard: List<Card>) {
    val hand: Hand = Hand(firstCard)

    fun draw(cardDeck: CardDeck) {
        hand.add(cardDeck.draw())
    }

    fun getHandSize(): Int = hand.getHandCount()

    fun getScore(): Int = hand.score()

}
