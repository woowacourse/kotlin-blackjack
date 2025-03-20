package blackjack.domain.model.betting

class BettingPlayers(private val value: List<BettingPlayer>) {
    fun get(name: String): BettingPlayer = requireNotNull(value.find { player -> player.name == name })
}
