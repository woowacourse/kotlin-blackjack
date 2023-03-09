package model
data class Participants(val dealer: Dealer, val players: Players) {

    fun toList(): List<Participant> = listOf(dealer as Participant) + players.toList() as List<Participant>
}
