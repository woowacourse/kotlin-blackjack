package blackjack.domain.model

class BetAmount(private val money: Money) {
    fun calculate(matchResult: MatchResult): Profit {
        return when (matchResult) {
            MatchResult.WIN -> Profit(money.value)
            MatchResult.LOSE -> Profit(money.value.times(-1))
            MatchResult.DRAW -> Profit(0.0)
            MatchResult.BLACKJACK -> Profit(money.value * 1.5)
        }
    }
}
