package model
data class Participants(val participants: List<Participant>) : List<Participant> by participants {
    companion object {
        fun of(dealer: Dealer, players: Players) = Participants(listOf(dealer as Participant) + players as List<Participant>)
    }
}
