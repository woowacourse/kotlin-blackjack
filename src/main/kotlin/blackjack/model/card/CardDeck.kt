package blackjack.model.card

import blackjack.model.card.generator.CardGenerator
import blackjack.model.card.generator.RandomCardGenerator

class CardDeck(private val cardGenerator: CardGenerator = RandomCardGenerator) {
    private val _cardDeck: List<Card> = create()
    val cardDeck: List<Card>
        get() = _cardDeck

    private fun create(): List<Card> =
        Suit.entries.flatMap { suit ->
            CardNumber.entries.map { number ->
                Card(suit, number)
            }
        }.shuffled()

    fun draw(): Card = cardGenerator.draw(cardDeck)
}
