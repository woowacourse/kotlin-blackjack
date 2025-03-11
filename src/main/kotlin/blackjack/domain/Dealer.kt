package blackjack.domain

class Dealer(
    private val players: List<Player>,
    shuffler: Shuffler,
) : Participant() {
    private val deck: Deck = Deck(shuffler)
    override val onBusted: () -> Unit = { playingPlayers.forEach(Player::win) }

    val dealerResults: List<PlayerState> = players.toDealerResult()

    fun draw() {
        draw(deck.draw())
    }

    fun pitch() {
        players.forEach { player -> player.draw(deck.draw()) }
    }

    fun giveCard(player: Player) {
        player.draw(deck.draw())
    }

    fun startPlayerTurn(turn: (Player) -> Unit) {
        players.forEach(turn)
    }

    fun startTurn() {
        while (score < 17) {
            draw(deck.draw())
        }
    }

    fun setResult() {
        playingPlayers.forEach { player -> player.setResult(score) }
    }

    private fun List<Player>.toDealerResult(): List<PlayerState> =
        map { player ->
            when (player.state) {
                PlayerState.WIN -> PlayerState.LOSE
                PlayerState.DRAW -> PlayerState.DRAW
                PlayerState.LOSE -> PlayerState.WIN
                PlayerState.PLAYING -> PlayerState.PLAYING
            }
        }

    private val playingPlayers: List<Player>
        get() = players.filter { player -> player.state == PlayerState.PLAYING }
}
