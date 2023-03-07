package domain

class BlackJackGame {

    private val deck: Deck = Deck.create(NUMBER_OF_CARD_SET)
    lateinit var players: Players
    lateinit var gameResults: List<GameResult>

    fun setUpBlackJackGame(
        readUserNames: () -> List<String>,
        readBetAmount: (String) -> Int
    ) {
        val userNames = readUserNames()
        val betAmounts = userNames.map { userName ->
            readBetAmount(userName)
        }
        players = Players(
            dealer = Dealer(cards = Cards(deck.getCards(CARD_PAIR))),
            users = createUsers(userNames.zip(betAmounts))
        )
    }

    private fun createUsers(userInitialValues: List<Pair<String, Int>>): List<User> {
        return userInitialValues.map { User.create(it, Cards(deck.getCards(CARD_PAIR))) }
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
        val userScore = Score.valueOf(user.cards.calculateCardValueSum())
        if (readMoreCardCommand(user) && (!userScore.isBurst() && !userScore.isBlackJack())) {
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
