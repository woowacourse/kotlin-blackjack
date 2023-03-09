package blackjack.domain.blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.card.Cards
import blackjack.domain.participants.Participants
import blackjack.domain.participants.ParticipantsBuilder

fun blackJack(block: BlackJackBuilder.() -> Unit): BlackJack {
    return BlackJackBuilder().apply(block).build()
}

class BlackJackBuilder {
    private var cardDeck: CardDeck = CardDeck(Cards.all().shuffled())
    var participants: Participants = Participants()

    fun participants(block: ParticipantsBuilder.() -> Unit) {
        participants = ParticipantsBuilder().apply(block).build()
    }

    fun initDrawAll() = participants.all.forEach {
        it.draw(cardDeck.drawCard())
        it.draw(cardDeck.drawCard())
    }

    fun build(): BlackJack = BlackJack(cardDeck, participants)
}
