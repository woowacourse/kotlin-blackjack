package model.participants

class Participants private constructor(private val participants: List<Participant>) {
    fun getDealer(): Dealer {
        return participants.first() as Dealer
    }

    fun getPlayers(): Players {
        return Players(participants.filterIsInstance<Player>())
    }

    fun getAll(): List<Participant> {
        return participants
    }

    companion object {
        fun of(
            dealer: Dealer,
            players: Players,
        ): Participants {
            return Participants(listOf(dealer) + players.players)
        }
    }
}
