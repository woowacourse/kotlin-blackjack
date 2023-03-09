package controller

import entity.Money
import entity.Name
import entity.Player
import entity.Players
import model.BlackjackStage
import model.CardFactory
import view.GameView
import view.InitView
import view.ResultView

class BlackjackController(
    private val initView: InitView,
    private val gameView: GameView,
    private val resultView: ResultView,
    private val cardFactory: CardFactory
) {

    private fun readPlayers(): Players {
        return initView.readPlayerNames().map {
            val money = initView.readPlayerMoney(it)
            Player(Name(it), Money(money))
        }.let { Players(it) }
    }

    private fun initBlackjack(): BlackjackStage =
        BlackjackStage(players = readPlayers(), cardFactory = cardFactory)

    private fun distributeMoreCardPlayer(blackjackStage: BlackjackStage) {
        blackjackStage.distributePlayers({
            gameView.printWhetherMoreCard(it.name.value)
            gameView.readWhetherMoreCard()
        }) {
            gameView.printPlayerStatus(it)
        }
    }

    private fun distributeMoreCardDealer(blackjackStage: BlackjackStage) {
        blackjackStage.distributeDealer {
            gameView.printDealerMoreCard()
        }
    }

    private fun displayUserStatus(blackjackStage: BlackjackStage) {
        gameView.printInitialUsersStatus(blackjackStage)
    }

    private fun displayGameStatus(blackjackStage: BlackjackStage) {
        resultView.printGameStatus(blackjackStage.dealer, blackjackStage.players)
    }

    private fun displayGameResult(blackjackStage: BlackjackStage) {
        val playersGameResult = blackjackStage.players.determineAllPlayerGameResult(blackjackStage.dealer)
        val dealerGameResult = playersGameResult.makeDealerGameResult()
        resultView.printGameResult(dealerGameResult, playersGameResult)
        resultView.printProfitResult(dealerGameResult, playersGameResult)
    }

    fun process() {
        val blackjackStage = initBlackjack()
        blackjackStage.distributeAllUsers()
        displayUserStatus(blackjackStage)
        distributeMoreCardPlayer(blackjackStage)
        distributeMoreCardDealer(blackjackStage)
        displayGameStatus(blackjackStage)
        displayGameResult(blackjackStage)
    }
}
