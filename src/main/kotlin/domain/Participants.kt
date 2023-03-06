package domain

class Participants(val players: Players, val dealer: Dealer) {
    val all: List<Participant>
        get() = listOf<Participant>(dealer) + players.list
}
