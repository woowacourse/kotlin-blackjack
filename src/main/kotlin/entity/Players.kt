package entity

class Players(val value: List<Player>) {
    fun determineAllPlayerGameResult(dealer: User): PlayersGameResult {
        val dealerCardNumberSum = dealer.cardsNumberSum()
        return value.associate {
            it.determineGameResult(dealerCardNumberSum)
        }.let { PlayersGameResult(it) }
    }
}
