package blackjack.model.player

import Player

class PlayerEntry(val players: List<Player>) {
    init {
        require(players.size <= MAX_PEOPLE) { "인원수는 최대 8인까지 가능합니다." }
        require(players.isNotEmpty()) { "최소 1명은 플레이 해야합니다." }
    }

    companion object {
        const val MAX_PEOPLE = 8
    }
}
