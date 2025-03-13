package blackjack.domain.model

class Participants(val dealer: Dealer, val players: List<Player>) {
    val participants get() = listOf(dealer, *players.toTypedArray())

    fun bets(betAmount: (String) -> Double): Bets {
        val bets =
            participants.map { participant ->
                Bet(participant, Money(betAmount(participant.name)))
            }
        return Bets(bets)
    }
}
