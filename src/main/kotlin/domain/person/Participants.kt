package domain.person

class Participants(val dealer: Dealer, val players: List<Player>) {

    init {
        checkPlayerNamesDuplicated()
    }

    private fun checkPlayerNamesDuplicated() {
        val playerNames = players.map { it.name }
        require(playerNames.size == playerNames.toSet().size)
    }

    companion object {
        fun from(getPlayerNames: () -> List<String>): Participants {
            val dealer = Dealer()
            val players = getPlayerNames().map { name -> Player(name) }
            return Participants(dealer, players)
        }
    }
}
