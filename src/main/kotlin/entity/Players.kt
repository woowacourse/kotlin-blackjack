package entity

class Players(val value: List<Player>) {
    fun determineAllPlayerGameResult(dealer: Dealer): PlayersGameResult {
        val dealerCardNumberSum = dealer.cards.sumOfNumbers()
        return value.associate {
            it.determineGameResult(dealerCardNumberSum)
        }.asSequence().associate {
            Pair(it.key, GameResult(it.value, it.key.calculateWinMoney(it.value)))
        }.let { PlayersGameResult(it) }
    }
}
