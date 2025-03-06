package blackjack.domain.model

class Participants(val players: List<Player>) {
    fun findDealer(): Dealer {
        return requireNotNull(players.find { it is Dealer }) as Dealer
    }

    fun filterPlayers(): List<Player> {
        return players.filterNot { it is Dealer }
    }
}
