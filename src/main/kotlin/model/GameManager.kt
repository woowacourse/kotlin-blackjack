package model

import model.GameResult.Companion.compareWinOrLose
import model.GameResult.Companion.decideProfitRates

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

    fun determinePlayersProfit(bettingManager: BettingManager): Map<Player, Int> {
        val playerResults = compareWinOrLose(dealer, players)
        val profitRates: Map<Player, Float> = decideProfitRates(playerResults)

        return players.associateWith { player ->
            val baseBet = bettingManager.getProfit(player)
            val multiplier = profitRates[player] ?: 0f
            (baseBet * multiplier).toInt()
        }
    }

    fun determineDealerProfit(playersProfit: Map<Player,Int>): Int {
        val totalPlayerProfit = playersProfit.values.sum()
        return -totalPlayerProfit
    }

    companion object {
        private const val INITIAL_DEALER_CARDS = 2
    }
}
