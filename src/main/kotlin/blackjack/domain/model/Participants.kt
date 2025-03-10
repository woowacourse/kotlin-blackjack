package blackjack.domain.model

class Participants(private val players: List<Participant>) {
    fun findDealer(): Dealer {
        return requireNotNull(players.find { it is Dealer } as? Dealer)
    }

    fun filterPlayers(): List<Participant> {
        return players.filterNot { it is Dealer }
    }
}
