package controller

import model.CardDistributor
import model.CardsGenerator
import model.Dealer
import model.GameResultDecider
import model.Hand
import model.Player
import model.Players
import model.displayNames
import view.InputView
import view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val cardsGenerator: CardsGenerator,
) {
    fun run() {
        val generatedCards = cardsGenerator.generateCards()
        val cardDistributor = CardDistributor(generatedCards)

        val initialDealerCards = cardDistributor.distributeInitialCards()
        val players = Players(getPlayers(inputView.inputPlayers(), cardDistributor))
        val dealer = Dealer(initialDealerCards)

        val playerCardNames: List<List<String>> =
            players.map { player -> player.getHand().handCards.displayNames() }

        showInitialGameState(players.getPlayersNames(), initialDealerCards, playerCardNames)
        handlePlayerTurns(players, cardDistributor)
        handleDealerTurn(dealer, cardDistributor)
        showTotalResult(initialDealerCards, dealer, players)
    }

    private fun showInitialGameState(
        playersNames: List<String>,
        initialDealerCards: Hand,
        playerCardsNames: List<List<String>>,
    ) {
        val dealerCardNames = initialDealerCards.handCards.displayNames()
        outputView.printDealerAndPlayers(playersNames)
        outputView.printInitialCards(dealerCardNames, playersNames, playerCardsNames)
    }

    private fun handlePlayerTurns(
        players: Players,
        cardDistributor: CardDistributor,
    ) {
        players.forEach { player ->
            while (player.decideToHit() && inputView.readHitOrStand(player.name)) {
                player.performTurn(cardDistributor)
                outputView.printPlayerCards(player.name, player.getHand().handCards.displayNames())
            }
        }
    }

    private fun showTotalResult(
        initialDealerCards: Hand,
        dealer: Dealer,
        players: Players,
    ) {
        val dealerCardNames = initialDealerCards.handCards.displayNames()
        outputView.printDealerResult(dealerCardNames, dealer.getScore())

        showPlayerResult(players, players.getPlayersNames())
        showGameResult(dealer, players)
    }

    private fun showPlayerResult(
        players: Players,
        playersNames: List<String>,
    ) {
        val updatedPlayerCardsNames: List<List<String>> =
            players.map { player -> player.getHand().handCards.displayNames() }
        val playersTotalScore = players.getPlayersScores()
        outputView.printPlayerResult(playersNames, updatedPlayerCardsNames, playersTotalScore)
    }

    private fun handleDealerTurn(
        dealer: Dealer,
        cardDistributor: CardDistributor,
    ) {
        if (dealer.decideToHit()) {
            val dealerAddCount = dealer.getDrawCount(cardDistributor)
            outputView.printDealerHit(dealerAddCount)
        }
    }

    private fun getPlayers(
        playersNames: List<String>,
        cardDistributor: CardDistributor,
    ): List<Player> =
        playersNames.map { name ->
            Player(name, cardDistributor.distributeInitialCards())
        }

    private fun showGameResult(
        dealer: Dealer,
        players: Players,
    ) {
        val gameResultOutput = GameResultDecider(dealer, players).compareWinOrLose()
        outputView.printResult(
            gameResultOutput.dealerWins,
            gameResultOutput.dealerLosses,
            gameResultOutput.playerResults,
        )
    }
}
