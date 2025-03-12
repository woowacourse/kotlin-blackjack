package model

class GameManager(private val cards: Cards) {
    private lateinit var dealer: Dealer
    private lateinit var players: Players

    fun getDealer(): Dealer = dealer

    fun getPlayers(): Players = players

    fun startGame(playerNames: List<String>) {
        dealer = Dealer(Hand(emptyList()))
        dealer.receiveCards(cards::drawCards)

        players = Players(playerNames.map { Player(it, Hand(emptyList())) })
        players.forEach { it.receiveCards(cards::drawCards) }
    }

    fun playersPlay(
        getPlayerDecision: (Player) -> Boolean,
        showCards: (Player) -> Unit,
    ) {
        players.forEach { player ->
            player.playTurn(
                shouldHit = getPlayerDecision,
                getCard = { cards.drawCards(1) },
                showCards = { showCards(player) },
            )
        }
    }

    fun dealerPlay() {
        dealer.playTurn { cards.drawCards(1) }
    }

    fun getDrawCount(): Int {
        return dealer.cards.size - INITIAL_DEALER_CARDS
    }

    fun determineGameResult(): GameOutput {
        return GameResultDecider(dealer, players).compareWinOrLose()
    }

    companion object {
        private const val INITIAL_DEALER_CARDS = 2
    }
}
