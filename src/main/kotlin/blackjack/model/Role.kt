package blackjack.model

abstract class Role {
    var cardSum = 0
        private set

    private val cards = mutableListOf<Card>()

    fun receiveCard(card: Card) {
        cards.add(card)
        cardSum += card.getValue()
    }

    abstract fun isBurst(): Boolean
}
