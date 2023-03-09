package domain.person

class Participants(val dealer: Dealer, val players: List<Player>) {

    init {
        checkPlayerNamesDuplicated()
        checkPlayerNameDoesNotEqualDealer()
    }

    private fun checkPlayerNamesDuplicated() {
        val playerNames = players.map { it.name }
        require(playerNames.size == playerNames.toSet().size)
    }

    private fun checkPlayerNameDoesNotEqualDealer() {
        val playerNames = players.map { it.name }
        require(dealer.name !in playerNames)
    }
}
