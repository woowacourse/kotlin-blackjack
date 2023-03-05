package domain

class Participants(val players: Players, val dealer: Dealer) {
    val participants
        get() = listOf<Participant>(dealer) + players.players

    inline fun forEach(action: (Participant) -> Unit) {
        for (element in participants) action(element)
    }
}
