package blackjack.model

data class Participants(
    val dealer: Dealer,
    val players: List<Player>,
) {
    fun getAllParticipants(): List<Participant> = listOf(dealer) + players
}
