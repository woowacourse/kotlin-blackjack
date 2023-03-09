package model

import entity.Dealer
import entity.Player
import entity.Players
import entity.User

class BlackjackStage(val dealer: Dealer, val players: Players, private val cardFactory: CardFactory) {
    fun distributeAllUsers() {
        distributeUser(dealer, INITIAL_CARD_DISTRIBUTE_COUNT)
        players.value.forEach {
            distributeUser(it, INITIAL_CARD_DISTRIBUTE_COUNT)
        }
    }

    fun distributePlayers(player: Player) {
        if (player.isDistributable()) {
            distributeUser(player, User.SINGLE_DISTRIBUTE_COUNT)
        }
    }

    fun distributeDealer(): Boolean {
        if (dealer.isDistributable()) {
            distributeUser(dealer, User.SINGLE_DISTRIBUTE_COUNT)
            return true
        }
        return false
    }

    private fun distributeUser(user: User, count: Int) {
        user.addCards(cardFactory.generate(count))
    }

    companion object {
        const val INITIAL_CARD_DISTRIBUTE_COUNT = 2
    }
}
