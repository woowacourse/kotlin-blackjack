package domain.person

class Participants2(val dealer: Dealer2, val players: List<Player2>) {

    init {
        checkPlayerNamesDuplicated()
    }

    private fun checkPlayerNamesDuplicated() {
        val playerNames = players.map { it.name }
        require(playerNames.size == playerNames.toSet().size)
    }
}
