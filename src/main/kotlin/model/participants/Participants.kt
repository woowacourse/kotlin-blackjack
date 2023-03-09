package model.participants

data class Participants(val dealer: Dealer, val players: Players) {

    fun toList(): List<Participant> = listOf(dealer) + players.toList()
}
