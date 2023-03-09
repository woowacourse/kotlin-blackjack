package domain

import domain.card.Deck
import domain.person.Dealer
import domain.person.Participants
import domain.person.Player

class ParticipantsBuilder(private val deck: Deck) {
    fun getInitialPersons(
        getPlayerNames: () -> List<String>,
        printInitParticipants: (Participants) -> Unit,
    ): Participants {
        val dealer = Dealer(deck.getCards(2))
        val players = getPlayerNames().map { name -> Player(deck.getCards(2), name) }
        val participants = Participants(dealer, players)
        printInitParticipants(participants)
        return participants
    }
}
