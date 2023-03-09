import domain.BlackJackGame
import domain.UserNameContainer
import view.GameResultView
import view.LoginView
import view.PlayGameView

class Controller(
    private val loginView: LoginView = LoginView(),
    private val playGameView: PlayGameView = PlayGameView(),
    private val gameResultView: GameResultView = GameResultView(),
) {

    fun run() {
        val blackJackGame = BlackJackGame()
        val blackJackGameData = blackJackGame.setUpBlackJackGame(::readUserNames, loginView::requestBetAmount)
        playGameView.printPlayers(players = blackJackGameData.players)
        blackJackGame.playDealerTurn(blackJackGameData, playGameView::printDealerPickNewCard)
        blackJackGame.playUserTurn(blackJackGameData, playGameView::isOneMoreCard, playGameView::printUserCard)
        gameResultView.printCardResult(blackJackGameData.players)
        blackJackGame.judgeGameResult(blackJackGameData, gameResultView::printFinalResult)
        blackJackGame.calculateProfit(blackJackGameData, gameResultView::printFinalProfit)
    }

    private fun readUserNames(): List<String> {
        val userNameContainer = repeatWithRunCatching { UserNameContainer(loginView.requestPlayerName()) }
        return userNameContainer.names
    }

    private fun <T> repeatWithRunCatching(action: () -> T): T {
        return runCatching(action).getOrElse { error ->
            println(error.message.toString())
            repeatWithRunCatching(action)
        }
    }
}
