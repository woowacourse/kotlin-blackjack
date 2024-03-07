package blackjack.model

object RandomCardProvider : CardProvider {
    override fun provide(cardBundle: List<Card>): Card {
        return cardBundle.shuffled().first()
    }
}
