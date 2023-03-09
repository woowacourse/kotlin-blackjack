package model

class BetInfosBuilder {
    private var infos = mutableMapOf<Player, Bet>()
    fun bet(player: Player, bet: Bet) {
        infos[player] = bet
    }

    fun build(): BetInfos = BetInfos(infos)
}
