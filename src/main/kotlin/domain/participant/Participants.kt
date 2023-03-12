package domain.participant

class Participants(val players: Players, val dealer: Dealer) {
    val list
        get() = listOf<Participant>(dealer) + players.list
}
