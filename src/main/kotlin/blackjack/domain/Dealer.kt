package blackjack.domain

class Dealer(
    private val players: List<Player>,
    shuffler: Shuffler,
) : Playable {
    override val cards: List<Card> get() = hand.cards
    override val score: Score get() = hand.score
    val playersName: List<String> get() = players.map { player -> player.name }
    val playersProfit: List<Int> get() = players.map { player -> player.profit }

    private val hand: Hand = Hand(onBusted = { playingPlayers.forEach(Player::win) })
    private val deck: Deck = Deck(shuffler)
    private val playingPlayers: List<Player>
        get() = players.filter { player -> player.state == ParticipantState.PLAYING }
    private val newCard get() = deck.draw()

    fun draw() {
        hand.draw(newCard)
    }

    fun pitch() {
        players.forEach { player -> player.draw(newCard) }
    }

    fun giveCard(player: Player) {
        player.draw(newCard)
    }

    fun startPlayerTurn(
        onStart: (Player) -> Unit,
        wantToHit: (Player) -> Boolean,
        afterHit: (Player) -> Unit,
    ) {
        players.forEach { player ->
            onStart(player)
            hitDuringWant(player, wantToHit, afterHit)
        }
    }

    private fun hitDuringWant(
        player: Player,
        wantToHit: (Player) -> Boolean,
        afterHit: (Player) -> Unit,
    ) {
        while (player.canHit) {
            if (!wantToHit(player)) break
            giveCard(player)
            afterHit(player)
        }
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
