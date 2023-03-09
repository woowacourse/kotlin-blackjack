import domain.BlackJackGame
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
        val blackJackGameData = blackJackGame.setUpBlackJackGame(loginView::requestPlayerName, loginView::requestBetAmount)
        playGameView.printPlayers(players = blackJackGameData.players)
        blackJackGame.playDealerTurn(blackJackGameData, playGameView::printDealerPickNewCard)
        blackJackGame.playUserTurn(blackJackGameData, playGameView::isOneMoreCard, playGameView::printUserCard)
        gameResultView.printCardResult(blackJackGameData.players)
        blackJackGame.judgeGameResult(blackJackGameData, gameResultView::printFinalResult)
        blackJackGame.calculateProfit(blackJackGameData, gameResultView::printFinalProfit)
    }
}
