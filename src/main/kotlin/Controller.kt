import domain.BlackJackGame
import domain.User
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
        blackJackGame.setUpBlackJackGame { readUserNames() }
        playGameView.printPlayers(players = blackJackGame.players)
        blackJackGame.playDealerTurn { printDealerPickNewCard() }
        blackJackGame.playUserTurn(::readMoreCardCommand, ::printUserCards)
        gameResultView.printCardResult(blackJackGame.players)
        blackJackGame.calculateGameResult()
        gameResultView.printFinalResult(blackJackGame.gameResults, blackJackGame.players)
    }

    private fun readUserNames(): List<String> {
        val userNameContainer = repeatWithRunCatching { getUserNames() }
        return userNameContainer.names
    }

    private fun getUserNames(): UserNameContainer = UserNameContainer(loginView.requestPlayerName())

    private fun printUserCards(user: User) = playGameView.printUserCard(user)

    private fun readMoreCardCommand(user: User): Boolean = playGameView.isOneMoreCard(user)

    private fun printDealerPickNewCard() = playGameView.printDealerPickNewCard()

    private fun <T> repeatWithRunCatching(action: () -> T): T {
        return runCatching(action).getOrElse { error ->
            println(error.message.toString())
            repeatWithRunCatching(action)
        }
    }
}
