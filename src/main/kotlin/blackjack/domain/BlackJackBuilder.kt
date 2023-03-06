package blackjack.domain

fun blackJack(block: BlackJackBuilder.() -> Unit): BlackJack {
    return BlackJackBuilder().apply(block).build()
}

class BlackJackBuilder {
    private lateinit var cardDeck: CardDeck
    private lateinit var participants: Participants
    fun cardDeck(cards: List<Card>) { cardDeck = CardDeck(cards.shuffled()) }

    fun participants(block: ParticipantsBuilder.() -> Unit) {
        participants = ParticipantsBuilder().apply(block).build()
    }

    fun draw() = participants.all().forEach {
        it.draw(cardDeck.nextCard())
        it.draw(cardDeck.nextCard())
    }

    fun build(): BlackJack {
        return BlackJack(cardDeck, participants)
    }
}
