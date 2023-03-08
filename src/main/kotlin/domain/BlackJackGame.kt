package domain

class BlackJackGame {

    private val deck: Deck = Deck.create(NUMBER_OF_CARD_SET)
    lateinit var players: Players

    fun setUpBlackJackGame(
        getUserNames: () -> List<String>,
        getBetAmount: (String) -> Int
    ) {
        val userNames = getUserNames()
        val betAmounts = userNames.map { userName ->
            getBetAmount(userName)
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
        onDealerOverSumCondition: () -> Unit
    ) {
        if (!players.dealer.isOverSumCondition()) {
            onDealerOverSumCondition()
            val newCard = deck.getOneCard()
            players.dealer.cards.addCard(newCard)
        }
    }

    fun playUserTurn(
        getMoreCardCommand: (User) -> Boolean,
        onUserPickNewCards: (User) -> Unit
    ) {
        players.users.forEach { user ->
            repeatGetCommand(user, getMoreCardCommand, onUserPickNewCards)
        }
    }

    private fun repeatGetCommand(
        user: User,
        getMoreCardCommand: (User) -> Boolean,
        onUserPickNewCards: (User) -> Unit
    ) {
        val userScore = Score.valueOf(user.cards.calculateCardValueSum())
        if (getMoreCardCommand(user) && (!userScore.isBurst() && !userScore.isBlackJack())) {
            user.cards.addCard(deck.getOneCard())
            onUserPickNewCards(user)
            return repeatGetCommand(user, getMoreCardCommand, onUserPickNewCards)
        }
        onUserPickNewCards(user)
        return
    }

    fun judgeGameResult(
        onGameResult: (List<User>) -> Unit
    ) {
        val referee: Referee = Referee(
            Score.valueOf(players.dealer.cards.actualCardValueSum()),
        )
        referee.getResult(players.users)
        onGameResult(players.users)
    }

    fun calculateProfit(
        onCalculateProfit: (Double, List<Pair<String, Double>>) -> Unit
    ) {
        val profitCalculator = ProfitCalculator(players)
        val dealerProfit = profitCalculator.getDealerProfit()
        val usersProfit = profitCalculator.getUsersProfit()
        onCalculateProfit(dealerProfit, usersProfit)
    }

    companion object {
        private const val NUMBER_OF_CARD_SET = 1
        private const val CARD_PAIR = 2
    }
}
