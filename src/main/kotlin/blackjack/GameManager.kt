package blackjack

class GameManager(
    private val players: List<Player>,
) {
    fun setting(cnt: Int = 2) {
        repeat(cnt) {
            players.forEach { player -> handOutCard(player) }
        }
    }

    fun handOutCard(player: Player) {
        player.addCard(Deck.poll())
    }
}
