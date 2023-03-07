package model

class BetInfosBuilder {
    private val infos = mutableMapOf<Player, Bet>()
    fun bet(player: Player, bet: Bet) {
        infos[player] = bet
    }
    fun build(): BetInfos {
        return BetInfos(infos.toMap())
    }
}
