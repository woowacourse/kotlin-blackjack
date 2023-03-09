package model

import entity.Player
import entity.User

class BlackjackStage(val users: Users, private val cardFactory: CardFactory) {
    fun distributeAllUsers() {
        users.distribute(cardFactory)
    }

    fun distributePlayers(distributeCondition: (player: Player) -> Boolean, printPlayerStatus: (player: Player) -> Unit) {
        for (player in users.players.value) {
            distributePlayer(player, distributeCondition, printPlayerStatus)
        }
    }

    private fun distributePlayer(player: Player, distributeCondition: (player: Player) -> Boolean, printPlayerStatus: (player: Player) -> Unit) {
        while (player.isDistributable() && distributeCondition(player)) {
            player.addMoreCards(cardFactory)
            printPlayerStatus(player)
        }
    }

    fun distributeDealer(printDealerStatus: () -> Unit) {
        if (users.dealer.isDistributable()) {
            users.dealer.cards.addCards(cardFactory.generate(User.SINGLE_DISTRIBUTE_COUNT))
            printDealerStatus()
        }
    }

    companion object {
        const val INITIAL_CARD_DISTRIBUTE_COUNT = 2
        const val WINNING_NUMBER = 21
    }
}
