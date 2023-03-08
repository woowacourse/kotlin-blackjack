package entity

class Players(val value: List<Player>) {
    fun determineAllPlayerGameResult(dealer: Dealer): PlayersGameResult {
        val dealerCardNumberSum = dealer.cards.sumOfNumbers()
        return value.associateWith {
            val gameResult = it.determineGameResult(dealerCardNumberSum)
            GameResult(gameResult, it.calculateWinMoney(gameResult))
        }.let { PlayersGameResult(it) }
    }
}
