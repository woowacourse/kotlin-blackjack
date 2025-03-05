package blackjack

class GameManager(
    private val players: List<Player>
) {
    fun handOutCard(player: Player) {
        player.addCard(Deck.poll())
    }
}
