package controller

import entity.CardDistributeCondition
import entity.Dealer
import entity.Name
import entity.Player
import entity.Players
import model.BlackjackStage
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
        blackjackStage.distributePlayers {
            gameView.printWhetherMoreCard(it.name.value)
            it.addMoreCards(CardDistributeCondition(gameView.readWhetherMoreCard())) {
                blackjackStage.distributePlayer(it)
                gameView.printPlayerStatus(it)
                distributeMoreCardPlayer(blackjackStage)
            }
        }
    }

    private fun distributeMoreCardDealer(blackjackStage: BlackjackStage) {
        blackjackStage.distributeDealer {
            gameView.printDealerMoreCard()
        }
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
