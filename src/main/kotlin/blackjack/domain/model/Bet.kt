package blackjack.domain.model

class Bet(val participant: Participant, private val money: Money) {
    fun calculate(matchResult: MatchResult): Profit {
        return when (matchResult) {
            MatchResult.WIN -> Profit(participant.name, money.value)
            MatchResult.LOSE -> Profit(participant.name, money.value.times(-1))
            MatchResult.DRAW -> Profit(participant.name, 0.0)
            MatchResult.BLACKJACK -> Profit(participant.name, money.value * 1.5)
        }
    }
}
