package blackjack.domain

class BlackJackBuilder {
    private lateinit var cardDeck: CardDeck
    private lateinit var participants: Participants
    fun cardDeck(cards: List<Card>) {
        cardDeck = CardDeck(cards)
    }

    fun participants(block: ParticipantsBuilder.() -> Unit) {
        participants = ParticipantsBuilder.init { block() }
    }

    fun draw() {
        participants.dealer.draw(cardDeck.nextCard())
        participants.dealer.draw(cardDeck.nextCard())
        participants.users.map {
            it.draw(cardDeck.nextCard())
            it.draw(cardDeck.nextCard())
        }
    }

    fun build(): BlackJack {
        return BlackJack(cardDeck, participants)
    }

    companion object {
        fun init(block: BlackJackBuilder.() -> Unit): BlackJack =
            BlackJackBuilder().apply(block).build()
    }
}
