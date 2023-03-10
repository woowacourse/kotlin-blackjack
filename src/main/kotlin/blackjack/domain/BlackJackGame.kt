package blackjack.domain

import blackjack.domain.carddeck.CardDeck

class BlackJackGame(names: List<String>, private val cardDeck: CardDeck) {
    val participants: Participants = Participants(names, ParticipantGenerator(cardDeck::drawCard))

    fun progressPlayersAddCard(getDecision: (Player) -> Boolean, printPlayerCard: (Player) -> Unit) {
        participants.players.progressPlayersAddCard(getDecision, printPlayerCard, cardDeck::drawCard)
    }

    fun progressDealerAddCard() {
        participants.dealer.progressAddCard(cardDeck::drawCard)
    }
}
