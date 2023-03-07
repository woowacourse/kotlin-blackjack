import domain.Cards
import domain.Dealer
import domain.Deck
import domain.GameResult
import domain.Referee
import domain.Score
import domain.User
import domain.UserNameContainer
import view.GameResultView
import view.LoginView
import view.PlayGameView

class Controller(
    private val loginView: LoginView = LoginView(),
    private val playGameView: PlayGameView = PlayGameView(),
    private val gameResultView: GameResultView = GameResultView(),
    private val deck: Deck = Deck.create(NUMBER_OF_CARD_SET),
) {

    fun run() {
        val userNames = readUserNames()
        val users = createUsers(userNames)
        val dealer = Dealer(cards = Cards(deck.getCards(2)))

        playGame(userNames, dealer, users)
        gameEnd(dealer, users)
    }

    private fun readUserNames(): List<String> {
        val userNameContainer = repeatWithRunCatching { getUserNames() }
        return userNameContainer.names
    }

    private fun getUserNames(): UserNameContainer = UserNameContainer(loginView.requestPlayerName())

    private fun createUsers(userNames: List<String>): List<User> {
        return userNames.map { name ->
            User(name, Cards(deck.getCards(2)))
        }
    }

    private fun playGame(userNames: List<String>, dealer: Dealer, users: List<User>) {
        playGameView.printNoticeSplitCard(userNames)
        playGameView.printPlayerCard(dealer, users)

        requestGetCommand(users)
        dealerPickNewCardIfNeeded(dealer)
    }

    private fun requestGetCommand(users: List<User>) {
        users.forEach { user ->
            repeatGetCommand(user)
        }
    }

    private fun repeatGetCommand(user: User) {
        if (getAnswer(user)) return userPickNewCard(user)
        return userPickNoCard(user)
    }

    private fun userPickNewCard(user: User) {
        user.cards.addCard(deck.getOneCard())
        playGameView.printUserCard(user)
        repeatGetCommand(user)
    }

    private fun userPickNoCard(user: User) {
        playGameView.printUserCard(user)
    }

    private fun getAnswer(user: User): Boolean = playGameView.isOneMoreCard(user)

    private fun dealerPickNewCardIfNeeded(dealer: Dealer) {
        if (!dealer.isOverSumCondition()) {
            playGameView.printDealerPickNewCard()
            val newCard = deck.getOneCard()
            dealer.cards.addCard(newCard)
        }
    }

    private fun gameEnd(dealer: Dealer, users: List<User>) {
        gameResultView.printCardResult(dealer, users)
        gameResultView.printFinalResult(getGameResult(dealer, users), users)
    }

    private fun getGameResult(dealer: Dealer, users: List<User>): List<GameResult> {
        val referee: Referee = Referee(
            Score.valueOf(dealer.cards.actualCardValueSum()),
            users.map { user ->
                Score.valueOf(user.cards.actualCardValueSum())
            },
        )
        return referee.getResult()
    }

    private fun <T> repeatWithRunCatching(action: () -> T): T {
        return runCatching(action).getOrElse { error ->
            println(error.message.toString())
            repeatWithRunCatching(action)
        }
    }

    companion object {
        private const val NUMBER_OF_CARD_SET = 1
    }
}
