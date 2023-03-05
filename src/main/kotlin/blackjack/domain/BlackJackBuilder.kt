package blackjack.domain

fun introduce(block: BlackJackBuilder.() -> Unit): BlackJack {
    return BlackJackBuilder().apply(block).build()
}

class BlackJackBuilder {
    private lateinit var cardDeck: CardDeck
    private lateinit var participants: Participants
    fun cardDeck(cards: List<Card>) {
        cardDeck = CardDeck(cards)
        cardDeck.shuffle()
    }

    fun participants(block: ParticipantsBuilder.() -> Unit) {
        participants = ParticipantsBuilder().apply(block).build()
    }

    fun draw() = participants.all().map {
        it.draw(cardDeck.nextCard())
        it.draw(cardDeck.nextCard())
    }

    fun build(): BlackJack {
        return BlackJack(cardDeck, participants)
    }
}
