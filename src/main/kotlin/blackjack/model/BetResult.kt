package blackjack.model

class BetResult(
    private val betResults: Map<Participant, WinningResult>,
) {
    fun getResult(
        dealer: Dealer,
        bets: Map<Player, Money>,
    ): Map<Participant, Money> {
        val playersResult = getPlayerResult(bets)
        val dealerResult = getDealerResult(playersResult, dealer)
        return playersResult + dealerResult
    }

    private fun getDealerResult(
        playerResults: Map<Participant, Money>,
        dealer: Dealer,
    ): Map<Participant, Money> {
        var dealerResult = Money(0.0)
        playerResults.forEach { (_, money) ->
            dealerResult + money
        }
        dealerResult = dealerResult.multiplyMoney(-1.0)
        return mapOf(Pair(dealer, dealerResult))
    }

    private fun getPlayerResult(bets: Map<Player, Money>): Map<Participant, Money> {
        val result = mutableMapOf<Player, Money>()
        bets.forEach {
            result[it.key] = getResultMoney(it.value, it.key)
        }
        return result.toMap()
    }

    private fun getResultMoney(
        betMoney: Money,
        participant: Participant,
    ): Money {
        betResults[participant]?.let { betResult ->
            return betMoney.multiplyMoney(WinningResult.getPrize(betResult))
        }
        throw IllegalArgumentException("BetResult not found")
    }

    companion object {
        fun makeBetResultByPlayers(
            players: Players,
            dealer: Dealer,
        ): BetResult {
            val betResults = mutableMapOf<Participant, WinningResult>()
            players.value.forEach { player ->
                betResults[player] = player.compareHand(dealer)
            }
            return BetResult(betResults)
        }
    }
}
