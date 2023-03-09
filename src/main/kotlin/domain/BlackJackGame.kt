package domain

class BlackJackGame {

    fun setUpBlackJackGame(
        getUserNames: () -> List<String>,
        getBetAmount: (String) -> Int
    ): BlackJackGameData {
        val userNames = getUserNames()
        val betAmounts = userNames.map { userName ->
            getBetAmount(userName)
        }
        val userBetAmounts = userNames.zip(betAmounts).map {
            UserBetAmount(it.first, it.second)
        }

        return makeBlackJackGameData(userBetAmounts)
    }

    private fun makeBlackJackGameData(userBetAmounts: List<UserBetAmount>): BlackJackGameData {
        val deck: Deck = Deck.create(NUMBER_OF_CARD_SET)
        val players = Players(
            dealer = Dealer(cards = Cards(deck.getCards(CARD_PAIR))),
            users = createUsers(userBetAmounts, deck)
        )
        return BlackJackGameData(deck, players)
    }

    private fun createUsers(userBetAmounts: List<UserBetAmount>, deck: Deck): List<User> {
        return userBetAmounts.map { User.create(it, Cards(deck.getCards(CARD_PAIR))) }
    }

    fun playDealerTurn(
        blackJackGameData: BlackJackGameData,
        onDealerOverSumCondition: () -> Unit
    ) {
        val dealer = blackJackGameData.dealer
        val deck = blackJackGameData.deck

        if (!dealer.isOverSumCondition()) {
            onDealerOverSumCondition()
            val newCard = deck.getOneCard()
            dealer.addCard(newCard)
        }
    }

    fun playUserTurn(
        blackJackGameData: BlackJackGameData,
        getMoreCardCommand: (User) -> Boolean,
        onUserPickNewCards: (User) -> Unit
    ) {
        val users = blackJackGameData.users
        users.forEach { user ->
            repeatGetCommand(blackJackGameData, user, getMoreCardCommand, onUserPickNewCards)
        }
    }

    private fun repeatGetCommand(
        blackJackGameData: BlackJackGameData,
        user: User,
        getMoreCardCommand: (User) -> Boolean,
        onUserPickNewCards: (User) -> Unit
    ) {
        val userScore = user.score
        if ((userScore.isBurst() || userScore.isBlackJack())) return
        if (getMoreCardCommand(user)) {
            user.addCard(blackJackGameData.deck.getOneCard())
            onUserPickNewCards(user)
            return repeatGetCommand(blackJackGameData, user, getMoreCardCommand, onUserPickNewCards)
        }
        onUserPickNewCards(user)
        return
    }

    fun judgeGameResult(
        blackJackGameData: BlackJackGameData,
        onGameResult: (List<User>) -> Unit
    ) {
        val players = blackJackGameData.players
        val referee: Referee = Referee(players.dealerScore)
        referee.getResult(players.users)
        onGameResult(players.users)
    }

    fun calculateProfit(
        blackJackGameData: BlackJackGameData,
        onCalculateProfit: (Double, List<UserProfit>) -> Unit
    ) {
        val profitCalculator = ProfitCalculator(blackJackGameData.players)
        val dealerProfit = profitCalculator.getDealerProfit()
        val usersProfit = profitCalculator.getUsersProfit()
        onCalculateProfit(dealerProfit, usersProfit)
    }

    companion object {
        private const val NUMBER_OF_CARD_SET = 1
        private const val CARD_PAIR = 2
    }
}
