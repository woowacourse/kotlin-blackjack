package domain

class BlackJackGame {

    private val deck: Deck = Deck.create(NUMBER_OF_CARD_SET)
    lateinit var players: Players
    lateinit var gameResults: List<GameResult>

    fun setUpBlackJackGame(
        readUserNames: () -> List<String>,
    ) {
        val userNames = readUserNames()
        players = Players(
            dealer = Dealer(cards = Cards(deck.getCards(CARD_PAIR))),
            users = createUsers(userNames)
        )
    }

    private fun createUsers(userNames: List<String>): List<User> {
        return userNames.map { name ->
            User(name, Cards(deck.getCards(CARD_PAIR)))
        }
    }

    fun playDealerTurn(
        printDealerPickNewCard: () -> Unit
    ) {
        if (!players.dealer.isOverSumCondition()) {
            printDealerPickNewCard()
            val newCard = deck.getOneCard()
            players.dealer.cards.addCard(newCard)
        }
    }

    fun playUserTurn(
        readMoreCardCommand: (User) -> Boolean,
        printUserCards: (User) -> Unit
    ) {
        players.users.forEach { user ->
            repeatGetCommand(user, readMoreCardCommand, printUserCards)
        }
    }

    private fun repeatGetCommand(
        user: User,
        readMoreCardCommand: (User) -> Boolean,
        printUserCards: (User) -> Unit
    ) {
        val isNotBurst = !Score.valueOf(user.cards.calculateCardValueSum()).isBurst()
        if (readMoreCardCommand(user) && isNotBurst) {
            user.cards.addCard(deck.getOneCard())
            printUserCards(user)
            return repeatGetCommand(user, readMoreCardCommand, printUserCards)
        }
        printUserCards(user)
        return
    }

    fun calculateGameResult() {
        val referee: Referee = Referee(
            Score.valueOf(players.dealer.cards.actualCardValueSum()),
            players.users.map { user ->
                Score.valueOf(user.cards.actualCardValueSum())
            },
        )
        gameResults = referee.getResult()
    }

    companion object {
        private const val NUMBER_OF_CARD_SET = 1
        private const val CARD_PAIR = 2
    }
}
