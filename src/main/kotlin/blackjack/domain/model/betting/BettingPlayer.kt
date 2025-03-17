package blackjack.domain.model.betting

import blackjack.domain.model.MatchResult
import blackjack.domain.model.Money
import blackjack.domain.model.Profit

class BettingPlayer(val name: String, private val money: Money) {
    fun calculate(matchResult: MatchResult): Profit = Profit(money * matchResult.profitRate)
}
