package domain

class ProfitCalculator(
    private val players: Players
) {

    fun getDealerProfit(): Double {
        var dealerProfit = NONE_PROFIT
        players.users.forEach { user ->
            dealerProfit += singleUserDealerProfit(user)
        }
        return dealerProfit
    }

    private fun singleUserDealerProfit(user: User): Double {
        return when (user.gameResult) {
            GameResult.LOSE -> user.betAmount
            GameResult.WIN -> dealerProfitWithUserBlackJack(user)
            GameResult.DRAW -> NONE_PROFIT
        }
    }

    private fun dealerProfitWithUserBlackJack(user: User): Double {
        var profit = -user.betAmount
        if (user.cards.isBlackJack()) profit -= user.betAmount * HALF
        return profit
    }

    fun getUsersProfit(): List<UserProfit> {
        val isDealerBlackJack = players.dealer.cards.isBlackJack()
        return players.users.map { user ->
            UserProfit(user, getUserProfit(isDealerBlackJack, user))
        }
    }

    private fun getUserProfit(isDealerBlackJack: Boolean, user: User): Double {
        if (isDealerBlackJack && !user.cards.isBlackJack()) return -user.betAmount
        if (user.cards.isBlackJack()) return user.betAmount * BLACKJACK_PROFIT_RATE
        return when (user.gameResult) {
            GameResult.WIN -> user.betAmount
            GameResult.DRAW -> NONE_PROFIT
            GameResult.LOSE -> -user.betAmount
        }
    }

    companion object {
        private const val BLACKJACK_PROFIT_RATE = 1.5
        private const val HALF = 0.5
        private const val NONE_PROFIT = 0.0
    }
}
