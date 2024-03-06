package blackjack.model.player

import Player

class PlayerEntry(val players: List<Player>) {
    init {
        require(players.isNotEmpty())
    }
}
