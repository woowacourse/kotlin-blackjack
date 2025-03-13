package blackjack.domain.model

class Participants(val dealer: Dealer, val players: List<Player>) {
    val participants get() = listOf(dealer, *players.toTypedArray())
}
