package blackjack.domain.blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.card.Cards
import blackjack.domain.participants.Participants
import blackjack.domain.participants.ParticipantsBuilder

fun blackJack(block: BlackJackBuilder.() -> Unit): BlackJack {
    return BlackJackBuilder().apply(block).build()
}

class BlackJackBuilder {
    private val cardDeck: CardDeck = CardDeck(Cards.all().shuffled())
    private lateinit var participants: Participants
    fun participants(block: ParticipantsBuilder.() -> Unit) {
        participants = ParticipantsBuilder().apply(block).build()
    }

    fun initDraw() {
        participants.all().forEach {
            it.draw(cardDeck.nextCard())
            it.draw(cardDeck.nextCard())
        }
    }

    fun build(): BlackJack = BlackJack(cardDeck, participants)
}
