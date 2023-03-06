package blackjack.domain.card

class RandomCardsGenerator {
    fun generate(): List<Card> {
        return CardNumber.values()
            .flatMap { number -> CardShape.values().map { shape -> Card(number, shape) } }
            .shuffled()
    }
}
