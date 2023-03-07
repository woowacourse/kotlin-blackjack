package blackjack.domain.card

class RandomCardsGenerator : CardsGenerator {
    override fun generate(): List<Card> {
        return CardNumber.values()
            .flatMap { number -> CardShape.values().map { shape -> Card(number, shape) } }
            .shuffled()
    }
}
