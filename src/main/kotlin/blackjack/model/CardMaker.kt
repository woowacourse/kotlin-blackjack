package blackjack.model

class CardMaker {
    val shuffledCards: List<Card> = cards.toMutableList().shuffled()

    companion object {
        val cards = combineCardSymbol()

        private fun combineCardSymbol(): List<Card> =
            Suit.entries.flatMap { suit ->
                CardNumber.entries.map { cardNumber -> Card(cardNumber, suit) }
            }
    }
}
