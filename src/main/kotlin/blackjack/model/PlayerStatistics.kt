package blackjack.model

class PlayerStatistics private constructor(
    private val playerStatistics: List<PlayerStatistic>,
) : Iterable<PlayerStatistic> by playerStatistics {

    constructor(
        dealer: Dealer,
        players: List<Player>,
    ) : this(
        players.map { player ->
            PlayerStatistic(player, player versus dealer)
        },
    )
}
