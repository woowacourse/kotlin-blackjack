package blackjack.model

interface CardDeck {
    fun generateCardDeck()

    fun draw(): Card
}
