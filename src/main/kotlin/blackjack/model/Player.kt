package blackjack.model

class Player(
    val name: String,
    override val hand: Hand,
    val betAmount: Amount
) : Participant {
    override fun settleBlackjack(amount: Amount) {
        money.addMoney(betAmount.toBlackjackMoney())
    }

    override fun settleWin(amount: Amount) {
        money.addMoney(betAmount)
        money.addMoney(betAmount)
    }

    override fun settlePush(amount: Amount) {
        money.addMoney(betAmount)
    }

    override fun settleLose(amount: Amount) {}

    override val money = betAmount.toMinus()
}
