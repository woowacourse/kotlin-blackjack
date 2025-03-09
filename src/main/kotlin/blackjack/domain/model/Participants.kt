package blackjack.domain.model

class Participants(val players: List<Participant>) {
    constructor(dealer: Dealer, playersName: List<String>) : this(listOf(dealer) + playersName.map(::Player))

    fun findDealer(): Dealer {
        return requireNotNull(players.find { it is Dealer } as? Dealer)
    }

    fun filterPlayers(): List<Participant> {
        return players.filterNot { it is Dealer }
    }
}
