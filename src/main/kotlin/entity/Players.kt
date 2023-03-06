package entity

class Players(val value: List<Player>) {
    fun determineAllPlayerGameResult(dealer: Dealer): PlayersGameResult {
        val dealerCardNumberSum = dealer.cards.sumOfNumbers()
        return value.associate {
            it.determineGameResult(dealerCardNumberSum)
        }.let { PlayersGameResult(it) }
    }
}
