package blackjack.domain.model

class BetAmount(private val money: Money) {
    fun calculate(verdictResult: VerdictResult): Profit {
        return when (verdictResult) {
            VerdictResult.WIN -> Profit(money.value)
            VerdictResult.LOSE -> Profit(money.value * -1)
            VerdictResult.DRAW -> Profit(0)
        }
    }
}
