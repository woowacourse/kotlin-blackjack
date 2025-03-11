package blackjack.domain

class Dealer(
    private val players: List<Player>,
    shuffler: Shuffler,
) : Participant() {
    override val onBusted: () -> Unit = { playingPlayers.forEach(Player::win) }
    private val deck: Deck = Deck(shuffler)

    val dealerResults: List<PlayerState>
        get() =
            players.map { player ->
                when (player.state) {
                    PlayerState.WIN -> PlayerState.LOSE
                    PlayerState.DRAW -> PlayerState.DRAW
                    PlayerState.LOSE -> PlayerState.WIN
                    PlayerState.PLAYING -> PlayerState.PLAYING
                }
            }

    fun draw() {
        hand.draw(deck.draw())
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

    fun startTurn(onEachTurn: () -> Unit) {
        while (handState is HandState.Score && score < 17) {
            onEachTurn()
            draw(deck.draw())
        }
    }

    fun setResult() {
        playingPlayers.forEach { player -> player.setResult(handState) }
    }

    private val playingPlayers: List<Player>
        get() = players.filter { player -> player.state == PlayerState.PLAYING }
}
