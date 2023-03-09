package model

import entity.users.Player
import entity.users.User
import entity.users.Users

class BlackjackStage(val users: Users, private val cardFactory: CardFactory) {
    fun distributeAllUsers() {
        distributeUser(users.dealer, INITIAL_CARD_DISTRIBUTE_COUNT)
        users.players.value.forEach {
            distributeUser(it, INITIAL_CARD_DISTRIBUTE_COUNT)
        }
    }

    fun distributePlayers(player: Player) {
        if (player.isDistributable()) {
            distributeUser(player, User.SINGLE_DISTRIBUTE_COUNT)
        }
    }

    fun distributeDealer(): Boolean {
        if (users.dealer.isDistributable()) {
            distributeUser(users.dealer, User.SINGLE_DISTRIBUTE_COUNT)
            return true
        }
        return false
    }

    private fun distributeUser(user: User, count: Int) = user.addCards(cardFactory.generate(count))

    companion object {
        const val INITIAL_CARD_DISTRIBUTE_COUNT = 2
    }
}
