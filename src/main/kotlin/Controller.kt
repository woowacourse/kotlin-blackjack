import domain.CardMachine
import domain.card.Card
import domain.player.Dealer
import domain.player.User
import domain.player.UserNameContainer
import view.* // ktlint-disable no-wildcard-imports
import view.Answer.Companion.YES

class Controller(
    private val gameIntroView: GameIntroView = GameIntroView(),
    private val playGameView: PlayGameView = PlayGameView(),
    private val gameResultView: GameResultView = GameResultView(),
    private val cardMachine: CardMachine = CardMachine(),
) {

    fun run() {
        val userNames = initUserName()
        val dealerCards = cardMachine.getCards(PAIR)
        val userCards = List(userNames.size) {
            cardMachine.getCards(PAIR)
        }
        val users = initUsers(userNames, userCards)
        val dealer = Dealer.create(dealerCards)

        playGame(userNames, dealer, users)
        gameEnd(dealer, users)
    }

    private fun playGame(userNames: List<String>, dealer: Dealer, users: List<User>) {
        playGameView.printNoticeSplitCard(userNames)
        playGameView.printPlayerCard(dealer, users)

        requestGetCommand(users)
        dealerPickNewCardIfNeed(dealer)
    }

    private fun gameEnd(dealer: Dealer, users: List<User>) {
        gameResultView.printCardResult(dealer, users)
        gameResultView.printFinalResult(getGameResult(dealer, users), users)
    }

    private fun getGameResult(dealer: Dealer, users: List<User>): List<GameResult> = users.map { user ->
        user.getResult(dealer)
    }

    private fun dealerPickNewCardIfNeed(dealer: Dealer) {
        if (!dealer.isOverSumCondition() and (dealer.addScoreTenIfHasAce() != 21)) {
            playGameView.printDealerPickNewCard()
            val newCard = cardMachine.getCards(1)
            dealer.addCard(newCard)
        }
    }

    private fun initUserName(): List<String> {
        val userNameContainer = repeatWithRunCatching { getUserNames() }
        return userNameContainer.names
    }

    private fun initUsers(userNames: List<String>, userCards: List<List<Card>>): List<User> =
        userNames.mapIndexed { card, userName -> User.create(userName, userCards[card]) }

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
        user.addCard(cardMachine.getCards(1))
        playGameView.printUserCard(user)
        repeatGetCommand(user)
    }

    private fun userPickNoCard(user: User) {
        playGameView.printUserCard(user)
    }

    private fun getAnswer(user: User): Answer =
        repeatWithRunCatching { Answer(playGameView.requestOneMoreCard(user)) }

    private fun getUserNames(): UserNameContainer = UserNameContainer(gameIntroView.requestPlayerName())

    private fun <T> repeatWithRunCatching(action: () -> T): T {
        return runCatching(action).getOrElse { error ->
            println(error.message.toString())
            repeatWithRunCatching(action)
        }
    }

    companion object {
        private const val PAIR = 2
    }
}
