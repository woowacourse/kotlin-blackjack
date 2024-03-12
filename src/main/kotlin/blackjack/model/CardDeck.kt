package blackjack.model

object CardDeck {
    val cardDeck =
        CardShape.entries.flatMap { shape ->
            CardNumber.entries.map { number ->
                Card(shape, number)
            }
        }.shuffled()
}
