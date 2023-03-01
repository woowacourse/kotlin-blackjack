package blackjack.domain

class RandomCardGenerator : CardGenerator {
    private val cards = Card.all()

    override fun generate(): Card = cards.shuffled().first()
}
