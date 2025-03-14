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

    fun determineBettingAmounts(bettingManager: BettingManager): Map<Player, Int> {
        val gameOutput = GameResultDecider(dealer, players).compareWinOrLose()

        return players.associateWith { player ->
            val playerResult = gameOutput.playerResults.firstOrNull { it.player == player }
            when (playerResult?.result) {
                GameResult.WIN -> bettingManager.getProfit(player)
                GameResult.LOSE -> -bettingManager.getProfit(player)
                GameResult.BLACKJACK -> (bettingManager.getProfit(player) * 1.5).toInt()
                GameResult.PUSH -> 0
                else -> 0
            }
        }
    }

    fun determineDealerProfit(bettingManager: BettingManager): Int {
        val totalPlayerProfit = determineBettingAmounts(bettingManager).values.sum()
        return -totalPlayerProfit
    }

    companion object {
        private const val INITIAL_DEALER_CARDS = 2
    }
}
