package controller

import entity.Dealer
import entity.Money
import entity.Name
import entity.Player
import entity.Players
import model.CardFactory
import model.Users
import view.GameView
import view.InitView
import view.ResultView

class BlackjackController(
    private val initView: InitView,
    private val gameView: GameView,
    private val resultView: ResultView
) {

    private fun readPlayers(): Players {
        return initView.readPlayerNames().map {
            val money = initView.readPlayerMoney(it)
            Player(Name(it), Money(money))
        }.let { Players(it) }
    }

    private fun initUsers(): Users = Users(readPlayers())

    private fun distributeMoreCardPlayer(cardFactory: CardFactory, players: Players) {
        players.distribute(cardFactory, {
            gameView.printWhetherMoreCard(it.name.value)
            gameView.readWhetherMoreCard()
        }, {
            gameView.printPlayerStatus(it)
        })
    }

    private fun distributeMoreCardDealer(cardFactory: CardFactory, dealer: Dealer) {
        dealer.distribute(cardFactory) {
            gameView.printDealerMoreCard()
        }
    }

    private fun displayUserStatus(users: Users) {
        gameView.printInitialUsersStatus(users)
    }

    private fun displayGameStatus(dealer: Dealer, players: Players) {
        resultView.printGameStatus(dealer, players)
    }

    private fun displayGameResult(users: Users) {
        val playersGameResult = users.players.determineAllPlayerGameResult(users.dealer)
        val dealerGameResult = playersGameResult.makeDealerGameResult()
        resultView.printGameResult(dealerGameResult, playersGameResult)
        resultView.printProfitResult(dealerGameResult, playersGameResult)
    }

    fun process(cardFactory: CardFactory) {
        val users = initUsers()
        users.distribute(cardFactory)
        displayUserStatus(users)
        distributeMoreCardPlayer(cardFactory, users.players)
        distributeMoreCardDealer(cardFactory, users.dealer)
        displayGameStatus(users.dealer, users.players)
        displayGameResult(users)
    }
}
