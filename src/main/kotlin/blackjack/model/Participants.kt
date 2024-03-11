package blackjack.model

import blackjack.model.Participant.Dealer
import blackjack.model.Participant.Player

class Participants(val participants: List<Participant>) {
    fun getDealer(): Dealer {
        return Dealer(participants[0].name, participants[0].gameInformation)
    }

    fun getPlayers(): List<Player> {
        return participants.subList(1, participants.size)
            .map { participant -> Player(participant.name, participant.gameInformation) }
    }
}
