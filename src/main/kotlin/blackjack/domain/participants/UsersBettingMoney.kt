package blackjack.domain.participants

class UsersBettingMoney(val usersBettingMoney: Map<User, BettingMoney>) {
    fun getUserBettingMoney(user: User): BettingMoney? = usersBettingMoney[user]
}
