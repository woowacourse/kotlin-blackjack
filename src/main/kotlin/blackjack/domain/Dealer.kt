package blackjack.domain

class Dealer(
    private val players: List<Player>,
    shuffler: Shuffler,
    hand: Hand = Hand(),
) : Participant(hand) {
    override val onBusted: () -> Unit = {
        val remainingPlayers = players.filter { player -> player.state == ParticipantState.PLAYING }
        remainingPlayers.forEach { player -> player.state = ParticipantState.WIN }
    }

    private val deck: Deck = Deck(shuffler)

    val participantStates: List<ParticipantState>
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
        draw(deck.draw())
    }

    fun pitch() {
        players.forEach { player -> player.draw(deck.draw()) }
    }

    fun giveCard(player: Player) {
        player.draw(deck.draw())
    }

    fun hitOrStay() {
        while (score < 17) {
            draw(deck.draw())
        }
    }
}
