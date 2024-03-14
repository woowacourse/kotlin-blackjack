package blackjack.model

data class Wallet(
    val name: String
) {
    private var money: Int = 0

    fun setResultMoney(resultMoney: Int) {
        money = resultMoney
    }

    fun getMoney(): Int {
        return money
    }
}
