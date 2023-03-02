package controller

import entity.Dealer
import entity.Player
import entity.Players
import model.BlackjackStage
import model.CardDistributor
import model.RandomCardFactory
import view.GameView
import view.InitView
import view.ResultView

class BlackjackController {
    private val initView = InitView()
    private val gameView = GameView()
    private val resultView = ResultView()

    private fun readPlayers(): Players {
        return initView.readPlayerNames().map {
            Player(it)
        }.let { Players(it) }
    }

    private fun initBlackjack(): BlackjackStage {
        val players = readPlayers()
        val dealer = Dealer()
        val cardFactory = RandomCardFactory()
        val cardDistributor = CardDistributor(cardFactory)
        val blackjackStage = BlackjackStage(dealer, players, cardDistributor)
        blackjackStage.distributeAllUsers()
        gameView.printInitialUsersStatus(dealer, players)
        return blackjackStage
    }

    private fun checkMoreCard(blackjackStage: BlackjackStage) {
        blackjackStage.players.requestAllPlayerReceiveMoreCard(
            { name -> gameView.printWhetherMoreCard(name) },
            { gameView.readWhetherMoreCard() }
        )
        blackjackStage.dealer.requestReceiveMoreCard(blackjackStage.cardDistributor)
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
        checkMoreCard(blackjackStage)
        displayGameStatus(blackjackStage)
        displayGameResult(blackjackStage)
    }
}
