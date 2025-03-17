package model

import model.GameResult.Companion.compareWinOrLose

class GameManager(private val cards: Cards) {
    private lateinit var dealer: Dealer
    private lateinit var players: Players

    fun getDealer(): Dealer = dealer

    fun getPlayers(): Players = players

    fun startGame(
        playerNames: List<String>,
        input: Map<String, Int>,
        bettingManager: BettingManager,
    ) {
        dealer = Dealer(Hand(emptyList()))
        dealer.receiveCards(cards::drawCards)

        players = Players(playerNames.map { Player(it, Hand(emptyList())) })
        players.forEach { it.receiveCards(cards::drawCards) }

        val betAmounts: Map<String, Int> = input

        players.forEach { player ->
            val bet = betAmounts[player.name] ?: 0
            bettingManager.placeBet(player, bet)
        }
    }

    fun playersPlay(showCards: (Player) -> Unit) {
        players.playersToPlayTurn(
            getCard = { cards.drawCards(1) },
            showCards = showCards,
        )
    }

    fun dealerPlay() {
        dealer.playTurn { cards.drawCards(1) }
    }

    fun getDrawCount(): Int {
        return dealer.cards.size - INITIAL_DEALER_CARDS
    }

    fun determineGameResults(
        bettingManager: BettingManager,
        profitCalculator: ProfitCalculator
    ): ProfitResult {
        val playerResults = compareWinOrLose(dealer, players)
        val playerProfits = profitCalculator.calculatePlayerProfits(playerResults, bettingManager)
        val dealerProfit = profitCalculator.dealerProfit(playerProfits)

        return ProfitResult(playerProfits, dealerProfit)
    }

    companion object {
        private const val INITIAL_DEALER_CARDS = 2
    }
}
