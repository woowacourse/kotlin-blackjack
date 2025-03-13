package blackjack.domain

class Dealer(
    private val players: List<Player>,
    shuffler: Shuffler,
) : Playable {
    override val cards: List<Card> get() = hand.cards
    override val score: Score get() = hand.score
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

    private val hand: Hand = Hand(onBusted = { playingPlayers.forEach(Player::win) })
    private val deck: Deck = Deck(shuffler)
    private val playingPlayers: List<Player>
        get() = players.filter { player -> player.state == ParticipantState.PLAYING }

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
        while (hand.canHit) {
            onEachTurn()
            draw()
        }
    }

    fun setResult() {
        playingPlayers.forEach { player -> player.setResult(score) }
    }
}
