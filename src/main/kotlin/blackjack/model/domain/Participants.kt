package blackjack.model.domain

interface Participants {
    val cards: MutableList<Card>

    val sumCardNumber get() = cards.sumOf { it.cardNumber.number }

    val cardDeck get() = cards.toList()

    fun receiveCard(card: Card) {
        cards.add(card)
    }
}
