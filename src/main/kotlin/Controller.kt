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
        blackJackGame.setUpBlackJackGame(::readUserNames, loginView::requestBetAmount)
        playGameView.printPlayers(players = blackJackGame.players)
        blackJackGame.playDealerTurn(playGameView::printDealerPickNewCard)
        blackJackGame.playUserTurn(playGameView::isOneMoreCard, playGameView::printUserCard)
        gameResultView.printCardResult(blackJackGame.players)
        blackJackGame.judgeGameResult(gameResultView::printFinalResult)
        blackJackGame.calculateProfit(gameResultView::printFinalProfit)
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
