package blackjack.model

class Players(
    val value: List<Player>,
) {
    fun pickCard(cardDeck: CardDeck) {
        value.forEach { player ->
            player.pickCard(cardDeck)
        }
    }

    fun getNotDyingPlayers(): List<Player> = value.filterNot { player -> player.isBust() }

    fun getDyingPlayers(): List<Player> = value.filter { player -> player.isBust() }
}
