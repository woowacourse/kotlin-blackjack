package model

import entity.Dealer
import entity.Player
import entity.Players
import entity.User

class BlackjackStage(val dealer: Dealer, val players: Players, private val cardFactory: CardFactory) {
    fun distributeAllUsers() {
        dealer.cards.addCards(cardFactory.generate(INITIAL_CARD_DISTRIBUTE_COUNT))
        players.value.forEach {
            it.cards.addCards(cardFactory.generate(INITIAL_CARD_DISTRIBUTE_COUNT))
        }
    }

    fun distributePlayers(distributeCondition: (player: Player) -> Boolean): Player? {
        players.value.forEach { it ->
            if (it.addMoreCards(cardFactory) { distributeCondition(it) })
                return it
        }
        return null
    }

    fun distributeDealer(printDealerStatus: () -> Unit) {
        if (dealer.isDistributable()) {
            dealer.cards.addCards(cardFactory.generate(User.SINGLE_DISTRIBUTE_COUNT))
            printDealerStatus()
        }
    }

    companion object {
        const val INITIAL_CARD_DISTRIBUTE_COUNT = 2
    }
}
