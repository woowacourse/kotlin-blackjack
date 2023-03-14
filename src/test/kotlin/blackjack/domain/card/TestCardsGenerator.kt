package blackjack.domain.card

class TestCardsGenerator(val cards: Cards) : CardsGenerator {
    override fun generate(): Cards = cards
}
