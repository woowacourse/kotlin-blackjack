package controller

import entity.card.CardDistributeCondition
import entity.result.BettingMoney
import entity.result.PlayersGameResult
import entity.result.UserBettingResult
import entity.users.Dealer
import entity.users.Name
import entity.users.Player
import entity.users.Players
import entity.users.UserInformation
import entity.users.Users
import model.BlackjackStage
import model.RandomCardFactory
import view.GameSetView
import view.GameView
import view.ResultView

class BlackjackController {
    private val gameSetView = GameSetView()
    private val gameView = GameView()
    private val resultView = ResultView()

    private fun readPlayersInformation(): Players {
        return gameSetView.getNames().map {
            val bettingMoney = gameSetView.getBetting(it)
            Player(UserInformation(Name(it), BettingMoney(bettingMoney)))
        }.let { Players(it) }
    }

    private fun initBlackjack(): BlackjackStage {
        val players = readPlayersInformation()
        val dealer = Dealer()
        val cardFactory = RandomCardFactory()
        val blackjackStage = BlackjackStage(Users(players, dealer), cardFactory)
        blackjackStage.distributeAllUsers()
        gameView.printInitStatus(dealer, players)
        return blackjackStage
    }

    private fun distributeMoreCardPlayer(blackjackStage: BlackjackStage) {
        blackjackStage.users.players.value.forEach { player ->
            distributeMoreCardPlayerProcess(player, blackjackStage)
        }
    }

    private fun distributeMoreCardPlayerProcess(player: Player, blackjackStage: BlackjackStage) {
        while (player.isDistributable()) {
            gameView.printMoreString1(player.userInformation.name.value)
            if (!player.isDistributable() || !CardDistributeCondition(gameView.getMoreString()).toBoolean()) break
            blackjackStage.distributePlayers(player)
            gameView.printPlayerStatus(player)
        }
    }

    private fun distributeMoreCardDealer(blackjackStage: BlackjackStage) {
        if (blackjackStage.distributeDealer()) gameView.printMoreString2()
    }

    private fun displayGameStatus(blackjackStage: BlackjackStage) =
        resultView.printStatus(blackjackStage.users.dealer, blackjackStage.users.players)

    private fun getGameResult(blackjackStage: BlackjackStage) =
        blackjackStage.users.players.determineAllPlayerGameResult(blackjackStage.users.dealer)

    private fun displayBettingResult(blackjackStage: BlackjackStage, playersGameResult: PlayersGameResult) {
        val users = Users(blackjackStage.users.players, blackjackStage.users.dealer)
        val userBettingResult = UserBettingResult(users, playersGameResult)
        val playersBettingResults = userBettingResult.getPlayersBettingResults()
        val dealerBettingResult = userBettingResult.getDealerBettingResult()
        resultView.printBettingResult(dealerBettingResult, playersBettingResults)
    }

    fun process() {
        val blackjackStage = initBlackjack()
        distributeMoreCardPlayer(blackjackStage)
        distributeMoreCardDealer(blackjackStage)
        displayGameStatus(blackjackStage)
        val playersGameResult = getGameResult(blackjackStage)
        displayBettingResult(blackjackStage, playersGameResult)
    }
}
