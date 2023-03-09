package controller

import entity.result.BettingMoney
import entity.card.CardDistributeCondition
import entity.users.Dealer
import entity.users.Name
import entity.users.Player
import entity.users.Players
import entity.result.PlayersGameResult
import entity.result.UserBettingResult
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
        return gameSetView.readPlayerNames().map {
            val bettingMoney = gameSetView.readBettingMoney(it)
            Player(UserInformation(Name(it), BettingMoney(bettingMoney)))
        }.let { Players(it) }
    }

    private fun initBlackjack(): BlackjackStage {
        val players = readPlayersInformation()
        val dealer = Dealer()
        val cardFactory = RandomCardFactory()
        val blackjackStage = BlackjackStage(Users(players, dealer), cardFactory)
        blackjackStage.distributeAllUsers()
        gameView.printInitialUsersStatus(dealer, players)
        return blackjackStage
    }

    private fun distributeMoreCardPlayer(blackjackStage: BlackjackStage) {
        blackjackStage.users.players.value.forEach { player ->
            distributeMoreCardPlayerProcess(player, blackjackStage)
        }
    }

    private fun distributeMoreCardPlayerProcess(player: Player, blackjackStage: BlackjackStage) {
        while (player.isDistributable()) {
            gameView.printWhetherMoreCard(player.userInformation.name.value)
            if (!player.isDistributable() || !CardDistributeCondition(gameView.readWhetherMoreCard()).toBoolean()) break
            blackjackStage.distributePlayers(player)
            gameView.printPlayerStatus(player)
        }
    }

    private fun distributeMoreCardDealer(blackjackStage: BlackjackStage) {
        if (blackjackStage.distributeDealer()) gameView.printDealerMoreCard()
    }

    private fun displayGameStatus(blackjackStage: BlackjackStage) =
        resultView.printGameStatus(blackjackStage.users.dealer, blackjackStage.users.players)

    private fun getGameResult(blackjackStage: BlackjackStage) =
        blackjackStage.users.players.determineAllPlayerGameResult(blackjackStage.users.dealer)

    private fun displayBettingResult(blackjackStage: BlackjackStage, playersGameResult: PlayersGameResult) {
        val userBettingResult = UserBettingResult()
        val users = Users(blackjackStage.users.players, blackjackStage.users.dealer)
        val playersBettingResults = userBettingResult.getPlayersBettingResults(users, playersGameResult)
        val dealerBettingResult = userBettingResult.getDealerBettingResult()
        resultView.printUserBettingResult(dealerBettingResult, playersBettingResults)
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
