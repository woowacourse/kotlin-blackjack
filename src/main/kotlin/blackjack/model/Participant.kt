package blackjack.model

interface Participant {

    val hand:Hand

    fun draw(cardDeck: CardDeck) {
        hand.add(cardDeck.draw())
    }

    fun getHandSize(): Int = hand.getHandCount()

    fun getScore(): Int = hand.score()

    fun isBusted(): Boolean = hand.isBust()

}
