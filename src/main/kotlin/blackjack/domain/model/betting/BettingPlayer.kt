package blackjack.domain.model.betting

import blackjack.domain.model.MatchResult
import blackjack.domain.model.Money
import blackjack.domain.model.Profit

class BettingPlayer(val name: String, private val money: Money) {
    fun calculate(matchResult: MatchResult): Profit {
        return when (matchResult) {
            MatchResult.WIN -> Profit(money * matchResult.profitRate)
            MatchResult.LOSE -> Profit(money * matchResult.profitRate)
            MatchResult.DRAW -> Profit(money * matchResult.profitRate)
            MatchResult.BLACKJACK -> Profit(money * matchResult.profitRate)
            MatchResult.BLACKJACK_LOSE -> Profit(money * matchResult.profitRate)
        }
    }
}
