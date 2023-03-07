package blackjack.domain.blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.participants.Participants
import blackjack.domain.participants.ParticipantsBuilder

fun blackJack(block: BlackJackBuilder.() -> Unit): BlackJack {
    return BlackJackBuilder().apply(block).build()
}

class BlackJackBuilder {
    private lateinit var cardDeck: CardDeck
    private lateinit var participants: Participants
    fun participants(block: ParticipantsBuilder.() -> Unit) {
        participants = ParticipantsBuilder().apply(block).build()
    }

    fun initDraw(cards: List<Card>) = participants.all().forEach {
        cardDeck = CardDeck(cards.shuffled())
        it.draw(cardDeck.nextCard())
        it.draw(cardDeck.nextCard())
    }

    fun build(): BlackJack = BlackJack(cardDeck, participants)
}
