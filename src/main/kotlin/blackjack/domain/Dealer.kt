package blackjack.domain

class Dealer(
    private val players: List<Player>,
    shuffler: Shuffler,
) : Participant() {
    override val onBusted: () -> Unit = { playingPlayers.forEach(Player::win) }
    private val deck: Deck = Deck(shuffler)

    val dealerResults: List<ParticipantState>
        get() =
            players.map { player ->
                when (player.state) {
                    ParticipantState.WIN -> ParticipantState.LOSE
                    ParticipantState.DRAW -> ParticipantState.DRAW
                    ParticipantState.LOSE -> ParticipantState.WIN
                    ParticipantState.PLAYING -> ParticipantState.PLAYING
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

    fun startDealerTurn(onEachTurn: () -> Unit) {
        while (score is Score.Hittable && score.value < 17) {
            onEachTurn()
            draw(deck.draw())
        }
    }

    fun setResult() {
        playingPlayers.forEach { player -> player.setResult(score) }
    }

    private val playingPlayers: List<Player>
        get() = players.filter { player -> player.state == ParticipantState.PLAYING }
}
