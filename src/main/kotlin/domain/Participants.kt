package domain

class Participants(val players: Players, val dealer: Dealer) {
    val participants
        get() = listOf<Participant>(dealer) + players.list
}
