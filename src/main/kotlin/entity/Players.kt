package entity

import model.CardDistributor
import model.RandomCardFactory

class Players(val value: List<Player>) {
    fun determineAllPlayerGameResult(dealer: User): PlayersGameResult {
        val dealerCardNumberSum = dealer.cardsNumberSum()
        return value.associate {
            it.determineGameResult(dealerCardNumberSum)
        }.let { PlayersGameResult(it) }
    }

    fun requestAllPlayerReceiveMoreCard(printMessage: (name: String) -> Unit, response: () -> String, printStatus: (player: Player) -> Unit) {
        val cardFactory = RandomCardFactory()
        val cardDistributor = CardDistributor(cardFactory)
        value.forEach {
            it.requestReceiveMoreCard(printMessage, response, printStatus, cardDistributor)
        }
    }
}
