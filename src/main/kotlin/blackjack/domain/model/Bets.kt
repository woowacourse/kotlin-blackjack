package blackjack.domain.model

class Bets(private val bets: List<Bet>) {
    fun getProfits(dealer: Dealer): Profits {
        val profits =
            bets.map { bet ->
                val matchResult = bet.participant.match(dealer)
                bet.calculate(matchResult)
            }
        return Profits(profits)
    }
}
