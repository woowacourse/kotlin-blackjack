package blackjack.model

class Player(val name: String, hand: Hand) : Participant(hand) {
    companion object {
        fun createPlayers(
            names: List<String>,
            deck: Deck,
        ): List<Player> {
            val players = names.map { Player(it, Hand(listOf())) }
            players.map { it.initialSetHand(deck) }
            return players
        }
    }
}
