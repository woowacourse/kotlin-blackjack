package blackjack.domain.model

class Participants(val players: List<Player>) {
    constructor(dealer: Dealer, playersName: List<String>) : this(listOf(dealer) + playersName.map(::Player))

    fun findDealer(): Dealer {
        return requireNotNull(players.filterIsInstance<Dealer>()).first()
    }

    fun filterPlayers(): List<Player> {
        return players.filterNot { it is Dealer }
    }
}
