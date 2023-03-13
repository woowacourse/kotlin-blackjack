package blackjack.domain.participants

import blackjack.domain.result.Outcome

class FinalProfit(private val profit: Map<User, Int>) {

    fun getUserProfit(user: User): Int? = profit.get(user)

    fun getDealerProfit(): Int {
        return profit.values.sum() * -1
    }

    companion object {

        fun playersFinalProfits(dealer: Dealer, usersBettingMoney: UsersBettingMoney): FinalProfit =
            FinalProfit(playerProfits(dealer, usersBettingMoney))

        private fun playerProfits(dealer: Dealer, usersBettingMoney: UsersBettingMoney): Map<User, Int> =
            usersBettingMoney.usersBettingMoney.map { (user, bettingMoney) ->
                val outcome = Outcome.getOutcome(user, dealer)
                user to bettingMoney.getProfits(outcome)
            }.toMap()
    }
}
