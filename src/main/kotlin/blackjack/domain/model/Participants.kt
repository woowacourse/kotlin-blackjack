package blackjack.domain.model

class Participants(initParticipants: List<Participant>) {
    val participants = initParticipants.sortedByDescending { it is Dealer }

    fun findDealer(): Dealer {
        return requireNotNull(participants.filterIsInstance<Dealer>().firstOrNull())
    }

    fun filterPlayers(): List<Participant> {
        return participants.filterNot { it is Dealer }
    }
}
