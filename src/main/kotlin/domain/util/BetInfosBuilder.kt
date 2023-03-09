package domain.util

import model.participants.Bet
import model.participants.BetInfo
import model.participants.Player

class BetInfosBuilder {
    private var infos = mutableMapOf<Player, Bet>()
    fun bet(player: Player, bet: Bet) {
        infos[player] = bet
    }

    fun build(): BetInfo = BetInfo(infos)
}
