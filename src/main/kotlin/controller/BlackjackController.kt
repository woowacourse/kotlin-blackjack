package controller

import model.card.Cards
import model.card.CardsGenerator
import model.participant.Dealer
import model.participant.Player
import model.participant.Players
import model.result.ProfitCalculator
import view.InputView
import view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val cardsGenerator: CardsGenerator,
) {
    fun run() {
        val allCards = cardsGenerator.generateCards()
        val initialDealerCards = allCards.initialCards()
        val playerNames = inputView.inputPlayerNames()
        val players = Players(createPlayers(playerNames, allCards))
        updateBet(players, inputView.inputBetAmount(playerNames))
        val dealer = Dealer(initialDealerCards)

        showInitialGameState(players, initialDealerCards)
        handlePlayerTurns(players, allCards)
        handleDealerTurn(dealer, allCards)
        showTotalResult(initialDealerCards, dealer, players)
    }

    private fun showTotalResult(
        dealerCards: Cards,
        dealer: Dealer,
        players: Players,
    ) {
        outputView.printDealerResult(dealerCards.names, dealer.score)
        showPlayerResult(players)
        showGameResult(dealer, players)
    }

    private fun showPlayerResult(players: Players) {
        val updatedPlayerCardsNames = players.cardNames
        val playersTotalScore = players.scores
        outputView.printPlayerResult(players.names, updatedPlayerCardsNames, playersTotalScore)
    }

    private fun showInitialGameState(
        players: Players,
        initialDealerCards: Cards,
    ) {
        outputView.printDealerAndPlayers(players.names)
        outputView.printInitialCards(initialDealerCards.names, players.names, players.cardNames)
    }

    private fun handleDealerTurn(
        dealer: Dealer,
        allCards: Cards,
    ) {
        if (dealer.canHit()) {
            val dealerAddCount = dealer.drawCount { allCards.drawCard() }
            outputView.printDealerHit(dealerAddCount)
        }
    }

    private fun handlePlayerTurns(
        players: Players,
        allCards: Cards,
    ) {
        players.forEach { player ->
            while (player.canHit() && inputView.readHitOrStand(player.name)) {
                val drawnCard = allCards.drawCard()
                player.turn(drawnCard)
                outputView.printPlayerCards(player.name, player.cardNames)
            }
        }
    }

    private fun showGameResult(
        dealer: Dealer,
        players: Players,
    ) {
        val profitCalculator = ProfitCalculator(dealer, players)
        outputView.printResult(profitCalculator.dealerProfit, profitCalculator.playerProfits)
    }

    private fun createPlayers(
        playerNames: List<String>,
        allCards: Cards,
    ): Players {
        val players =
            playerNames.map { name ->
                Player(name, allCards.initialCards())
            }
        return Players(players)
    }

    private fun updateBet(
        players: List<Player>,
        betAmounts: List<Float>,
    ) {
        players.forEachIndexed { index, player ->
            player.betting(betAmounts[index])
        }
    }
}
