package blackjack.domain.blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.card.Cards
import blackjack.domain.participants.Participants
import blackjack.domain.participants.ParticipantsBuilder
import blackjack.domain.state.inTurn.FirstTurn

fun blackJackSetting(block: BlackJackBuilder.() -> Unit): BlackJackSetting {
    return BlackJackBuilder().apply(block).build()
}

class BlackJackBuilder {
    private var cardDeck: CardDeck = CardDeck(Cards.all().shuffled())
    var participants: Participants = Participants()

    fun participants(block: ParticipantsBuilder.() -> Unit) {
        participants = ParticipantsBuilder().apply(block).build()
    }

    fun initDrawAll() = participants.all.forEach {
        it.state = FirstTurn().draw(cardDeck.drawCard()).draw(cardDeck.drawCard())
    }

    fun build(): BlackJackSetting = BlackJackSetting(cardDeck, participants)
}
