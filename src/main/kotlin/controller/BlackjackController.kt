package controller

import entity.CardDistributeCondition
import entity.Dealer
import entity.Name
import entity.Player
import entity.Players
import model.BlackjackStage
import model.RandomCardFactory
import view.GameSetView
import view.GameView
import view.ResultView

class BlackjackController {
    private val gameSetView = GameSetView()
    private val gameView = GameView()
    private val resultView = ResultView()

    private fun readPlayers(): Players {
        return gameSetView.readPlayerNames().map {
            Player(Name(it))
        }.let { Players(it) }
    }

    private fun initBlackjack(): BlackjackStage {
        val players = readPlayers()
        val dealer = Dealer()
        val cardFactory = RandomCardFactory()
        val blackjackStage = BlackjackStage(dealer, players, cardFactory)
        blackjackStage.distributeAllUsers()
        gameView.printInitialUsersStatus(dealer, players)
        return blackjackStage
    }

    private fun distributeMoreCardPlayer(blackjackStage: BlackjackStage) {
        blackjackStage.players.value.forEach { player ->
            distributeMoreCardPlayerProcess(player, blackjackStage)
        }
    }

    private fun distributeMoreCardPlayerProcess(player: Player, blackjackStage: BlackjackStage) {
        while (player.isDistributable()) {
            gameView.printWhetherMoreCard(player.name.value)
            if (!player.isDistributable() || !CardDistributeCondition(gameView.readWhetherMoreCard()).toBoolean()) break
            blackjackStage.distributePlayers(player)
            gameView.printPlayerStatus(player)
        }
    }

    private fun distributeMoreCardDealer(blackjackStage: BlackjackStage) {
        if (blackjackStage.distributeDealer()) gameView.printDealerMoreCard()
    }

    private fun displayGameStatus(blackjackStage: BlackjackStage) {
        resultView.printGameStatus(blackjackStage.dealer, blackjackStage.players)
    }

    private fun displayGameResult(blackjackStage: BlackjackStage) {
        val playersGameResult = blackjackStage.players.determineAllPlayerGameResult(blackjackStage.dealer)
        val dealerGameResult = playersGameResult.makeDealerGameResult()
        resultView.printGameResult(dealerGameResult, playersGameResult)
    }

    fun process() {
        val blackjackStage = initBlackjack()
        distributeMoreCardPlayer(blackjackStage)
        distributeMoreCardDealer(blackjackStage)
        displayGameStatus(blackjackStage)
        displayGameResult(blackjackStage)
    }
}
