package model.tools

import model.domain.player.Dealer
import model.domain.player.User

class Participant private constructor(val dealer: Dealer, val user: List<User>) {
    companion object {
        fun from(getPlayerNames: () -> List<String>): Participant {
            val dealer = Dealer.from()
            val users = getPlayerNames().map { name ->
                User.from(name)
            }
            return Participant(dealer, users)
        }
    }
}
