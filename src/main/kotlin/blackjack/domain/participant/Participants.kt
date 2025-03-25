package blackjack.domain.participant

class Participants(
    val dealer: Dealer,
    val players: List<Player>,
) {
    val list = listOf(dealer) + players
}
