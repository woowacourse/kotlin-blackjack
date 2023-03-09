package entity

import model.CardFactory

class Players(val value: List<Player>) {
    fun determineAllPlayerGameResult(dealer: Dealer): PlayersGameResult {
        val dealerCardNumberSum = dealer.cards.sumOfNumbers()
        return value.associateWith {
            val gameResult = it.determineGameResult(dealerCardNumberSum)
            GameResult(gameResult, it.calculateWinMoney(gameResult))
        }.let { PlayersGameResult(it) }
    }

    fun distribute(
        cardFactory: CardFactory,
        distributeCondition: (player: Player) -> Boolean,
        printPlayerStatus: (player: Player) -> Unit
    ) {
        for (player in value) {
            player.distribute(cardFactory, distributeCondition, printPlayerStatus)
        }
    }
}
