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
        val deck: Deck = Deck.create(NUMBER_OF_CARD_SET)
        val players = Players(
            dealer = Dealer(cards = Cards(deck.getCards(CARD_PAIR))),
            users = createUsers(userNames.zip(betAmounts), deck)
        )
        return BlackJackGameData(deck, players)
    }

    private fun createUsers(userInitialValues: List<Pair<String, Int>>, deck: Deck): List<User> {
        return userInitialValues.map { User.create(it, Cards(deck.getCards(CARD_PAIR))) }
    }

    fun playDealerTurn(
        blackJackGameData: BlackJackGameData,
        onDealerOverSumCondition: () -> Unit
    ) {
        val dealer = blackJackGameData.players.dealer
        val deck = blackJackGameData.deck

        if (!dealer.isOverSumCondition()) {
            onDealerOverSumCondition()
            val newCard = deck.getOneCard()
            dealer.cards.addCard(newCard)
        }
    }

    fun playUserTurn(
        blackJackGameData: BlackJackGameData,
        getMoreCardCommand: (User) -> Boolean,
        onUserPickNewCards: (User) -> Unit
    ) {
        val players = blackJackGameData.players
        players.users.forEach { user ->
            repeatGetCommand(blackJackGameData, user, getMoreCardCommand, onUserPickNewCards)
        }
    }

    private fun repeatGetCommand(
        blackJackGameData: BlackJackGameData,
        user: User,
        getMoreCardCommand: (User) -> Boolean,
        onUserPickNewCards: (User) -> Unit
    ) {
        val userScore = Score.valueOf(user.cards.calculateCardValueSum())
        if ((userScore.isBurst() || userScore.isBlackJack())) return
        if (getMoreCardCommand(user)) {
            user.cards.addCard(blackJackGameData.deck.getOneCard())
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
        val referee: Referee = Referee(
            Score.valueOf(players.dealer.cards.actualCardValueSum()),
        )
        referee.getResult(players.users)
        onGameResult(players.users)
    }

    fun calculateProfit(
        blackJackGameData: BlackJackGameData,
        onCalculateProfit: (Double, List<Pair<String, Double>>) -> Unit
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
