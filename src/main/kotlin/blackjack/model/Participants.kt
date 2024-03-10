package blackjack.model

import blackjack.model.Participant.Dealer
import blackjack.model.Participant.Player

class Participants(val dealer: Dealer, val players: List<Player>) {
    fun getParticipants(): List<Participant> {
        val participants = mutableListOf<Participant>()
        participants.add(dealer)
        participants.addAll(players)
        return participants
    }
}
