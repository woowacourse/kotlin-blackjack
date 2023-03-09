package controller

import controller.ViewUtils.Companion.toFormattedString
import entity.Dealer
import entity.GameResultType
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
            gameView.printPlayerStatus(it.name.value, it.cards.toFormattedString())
        })
    }

    private fun distributeMoreCardDealer(cardFactory: CardFactory, dealer: Dealer) {
        dealer.distribute(cardFactory) {
            gameView.printDealerMoreCard()
        }
    }

    private fun displayUserStatus(users: Users) {
        gameView.printInitialUsersStatus(users.players.value.map { it.name.value })
        gameView.printDealerStatus(users.dealer.pickFirstCard().toFormattedString())
        users.players.value.forEach { gameView.printPlayerStatus(it.name.value, it.cards.toFormattedString()) }
    }

    private fun displayGameStatus(dealer: Dealer, players: Players) {
        resultView.printDealerStatus(dealer.cards.toFormattedString(), dealer.cards.sumOfNumbers())
        resultView.printPlayerStatus(
            players.value.map { it.name.value },
            players.value.map { it.cards.toFormattedString() },
            players.value.map { it.cards.sumOfNumbers() }
        )
    }

    private fun displayGameResult(users: Users) {
        val playersGameResult = users.players.determineAllPlayerGameResult(users.dealer)
        val dealerGameResult = playersGameResult.makeDealerGameResult()
        resultView.printGameResult(
            dealerGameResult.value.asSequence().associate { gameResultTypeToString(it.key) to it.value },
            playersGameResult.value.asSequence()
                .associate { it.key.name.value to gameResultTypeToString(it.value.type) }
        )
        resultView.printProfitResult(
            dealerGameResult.profit.value,
            playersGameResult.value.keys.map { it.name.value },
            playersGameResult.value.values.map { it.profit.value }
        )
    }

    private fun gameResultTypeToString(gameResultType: GameResultType): String {
        return when (gameResultType) {
            GameResultType.WIN -> "승"
            GameResultType.LOSE -> "패"
            GameResultType.DRAW -> "무"
        }
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
