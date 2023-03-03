package model

import entity.Dealer
import entity.Player
import entity.Players
import entity.User

class BlackjackStage(val dealer: Dealer, val players: Players, val cardFactory: CardFactory) {
    fun distributeAllUsers() {
        dealer.addCards(cardFactory.generate(INITIAL_CARD_DISTRIBUTE_COUNT))
        players.value.forEach {
            it.addCards(cardFactory.generate(INITIAL_CARD_DISTRIBUTE_COUNT))
        }
    }

    fun distributePlayers(onDistribute: (player: Player) -> Unit) {
        players.value.forEach {
            if (it.isDistributable()) {
                onDistribute(it)
            }
        }
    }

    fun distributePlayer(player: Player) {
        player.addCards(cardFactory.generate(User.SINGLE_DISTRIBUTE_COUNT))
    }

    fun distributeDealer(printDealerStatus: () -> Unit) {
        if (dealer.isDistributable()) {
            dealer.addCards(cardFactory.generate(User.SINGLE_DISTRIBUTE_COUNT))
            printDealerStatus()
        }
    }

    companion object {
        const val INITIAL_CARD_DISTRIBUTE_COUNT = 2
    }
}
