package model
data class Participants(private val participants: List<Participant>) {
    fun toList(): List<Participant> = participants.toList()
    companion object {
        fun of(dealer: Dealer, players: Players) = Participants(listOf(dealer as Participant) + players as List<Participant>)
    }
}
