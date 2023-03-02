package blackjack.domain

object BlackJackBuilder {
    private lateinit var participants: Participants
    private lateinit var cardDeck: CardDeck

    fun cardDeck(generator: CardGenerator) {
        cardDeck = CardDeck(generator)
    }

    fun participants(block: ParticipantsBuilder.() -> Unit) {
        participants = ParticipantsBuilder(block)
    }

    operator fun invoke(block: BlackJackBuilder.() -> Unit): BlackJack =
        BlackJackBuilder.apply(block).build()

    private fun build(): BlackJack = BlackJack(cardDeck, participants)
}
