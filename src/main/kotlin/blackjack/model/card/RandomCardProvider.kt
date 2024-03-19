package blackjack.model.card

object RandomCardProvider : CardProvider {
    override fun provide(): Card {
        return Card.bundle.shuffled().first()
    }
}
