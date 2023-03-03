import domain.* // ktlint-disable no-wildcard-imports
import domain.Answer.Companion.YES
import view.GameResultView
import view.LoginView
import view.PlayGameView

class Controller(
    private val loginView: LoginView = LoginView(),
    private val playGameView: PlayGameView = PlayGameView(),
    private val gameResultView: GameResultView = GameResultView(),
    private val cardMachine: CardMachine = CardMachine(),

) {

    fun run() {
        val userNames = initUserName()
        val dealerCards = cardMachine.getCardPair()
        val userCards = cardMachine.getCardPairs(userNames.size)
        val users = initUsers(userNames, userCards)
        val dealer = Dealer.create(dealerCards)

        playGame(userNames, dealer, users)
        gameEnd(dealer, users)
    }

    private fun playGame(userNames: List<String>, dealer: Dealer, users: List<User>) {
        playGameView.printNoticeSplitCard(userNames)
        playGameView.printPlayerCard(dealer, users)

        requestGetCommand(users)
        dealerPickNewCardIfNeeded(dealer)
    }

    private fun gameEnd(dealer: Dealer, users: List<User>) {
        gameResultView.printCardResult(dealer, users)
        gameResultView.printFinalResult(getGameResult(dealer, users), users)
    }

    private fun getGameResult(dealer: Dealer, users: List<User>): List<GameResult> {
        val referee: Referee = Referee(
            dealer.validPlayerSum(),
            users.map { user ->
                user.validPlayerSum()
            },
        )
        return referee.getResult()
    }

    private fun dealerPickNewCardIfNeeded(dealer: Dealer) {
        if (!dealer.isOverSumCondition()) {
            playGameView.printDealerPickNewCard()
            val newCard = cardMachine.getNewCard()
            dealer.addCard(newCard)
        }
    }

    private fun initUserName(): List<String> {
        val userNameContainer = repeatWithRunCatching { getUserNames() }
        return userNameContainer.names
    }

    private fun initUsers(userNames: List<String>, userCards: List<List<Card>>): List<User> {
        val userNamesAndCards = userNames.zip(userCards)
        return userNamesAndCards.map { userNameAndCard ->
            User.create(userNameAndCard)
        }
    }

    private fun requestGetCommand(users: List<User>) {
        users.forEach { user ->
            repeatGetCommand(user)
        }
    }

    private fun repeatGetCommand(user: User) {
        val answer = getAnswer(user)
        if (answer.value == YES) return userPickNewCard(user)
        return userPickNoCard(user)
    }

    private fun userPickNewCard(user: User) {
        user.addCard(cardMachine.getNewCard())
        playGameView.printUserCard(user)
        repeatGetCommand(user)
    }

    private fun userPickNoCard(user: User) {
        playGameView.printUserCard(user)
    }

    private fun getAnswer(user: User): Answer =
        repeatWithRunCatching { Answer(playGameView.requestOneMoreCard(user)) }

    private fun getUserNames(): UserNameContainer = UserNameContainer(loginView.requestPlayerName())

    private fun <T> repeatWithRunCatching(action: () -> T): T {
        return runCatching(action).getOrElse { error ->
            println(error.message.toString())
            repeatWithRunCatching(action)
        }
    }
}
