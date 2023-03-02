import domain.*
import domain.Answer.Companion.NO
import domain.Answer.Companion.YES
import view.LoginView
import view.PlayGameView

class Controller(
    private val loginView: LoginView = LoginView(),
    private val playGameView: PlayGameView = PlayGameView(),
) {

    fun run() {
        val userNameContainer = repeatWithRunCatching { getUserNames() }
        val dealerCards = CardMachine.getCardPair()
        val userCards = CardMachine.getCardPairs(userNameContainer.names.size)
        val userNames = userNameContainer.names

        val userNamesAndCards = userNames.zip(userCards)
        val users = userNamesAndCards.map { userNameAndCard ->
            User.create(userNameAndCard)
        }
        val dealer = Dealer.create(dealerCards)
        playGameView.printNoticeSplitCard(userNames)
        playGameView.printPlayerCard(dealer, users)

        users.forEach { user ->
            repeatGetCommand(user)
        }
    }

    private fun repeatGetCommand(user: User) {
        val answer = getAnswer(user)
        if (answer.value == YES) {
            user.addCard(CardMachine.getNewCard())
            playGameView.printUserCard(user)
            repeatGetCommand(user)
        }

        if (answer.value == NO) {
            playGameView.printUserCard(user)
        }
    }

    private fun getAnswer(user: User): Answer {
        return repeatWithRunCatching { Answer(playGameView.requestOneMoreCard(user)) }
    }

    private fun getUserNames(): UserNameContainer = UserNameContainer(loginView.requestPlayerName())

    private fun <T> repeatWithRunCatching(action: () -> T): T {
        return runCatching(action).getOrElse { error ->
            println(error.message.toString())
            repeatWithRunCatching(action)
        }
    }

    companion object {
        private const val DEALER = 1
    }
}
