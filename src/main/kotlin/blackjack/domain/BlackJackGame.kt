package blackjack.domain

import blackjack.domain.carddeck.CardDeck

class BlackJackGame(names: List<String>, private val cardDeck: CardDeck) {
    val participants: Participants = Participants(names, ParticipantGenerator(cardDeck::drawCard))

    fun progressPlayersAddCard(getDecision: (Player) -> Boolean, transferPlayerCard: (Player) -> Unit) {
        participants.players.progressPlayersAddCard(getDecision, transferPlayerCard, cardDeck::drawCard)
    }

    fun progressDealerAddCard(transferDealerCondition: (Boolean) -> Unit) {
        participants.dealer.progressAddCard(cardDeck::drawCard, transferDealerCondition)
    }
}
