package blackjack.model

abstract class Role {
    private var cardSum = 0

    private val cards = mutableListOf<Card>()

    fun receiveCard(card: Card) {
        cards.add(card)
        cardSum += card.getValue()
    }
}
