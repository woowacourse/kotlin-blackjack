package blackjack.domain.card

class RandomCardsGenerator : CardsGenerator {
    override fun generate(): Cards {
        return Cards(
            CardNumber.values()
                .flatMap { number -> CardShape.values().map { shape -> Card(number, shape) } }
                .shuffled()
        )
    }
}
