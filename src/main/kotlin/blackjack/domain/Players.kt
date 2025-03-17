package blackjack.domain

import blackjack.domain.state.Ready

class Players(
    private val _players: List<Player>,
) {
    init {
        require(_players.size in 1..8) { "참가자는 최소 1명에서 최대 8명까지만 가능합니다." }
    }

    val players: List<Player>
        get() = _players

    companion object {
        fun from(
            names: List<String>,
            getMoreCard: (String) -> Boolean,
            battingAmountProvider: (String) -> Int,
        ): Players =
            Players(
                names.map { name ->
                    Player(name, Ready(Hand(emptyList(), battingAmountProvider(name))), getMoreCard)
                },
            )
    }

    fun initializeCards(giveCard: () -> Card) {
        _players.forEach { it.drawInitialCards(giveCard) }
    }

    fun drawMoreCards(
        giveCard: () -> Card,
        printCards: (String, Hand) -> Unit,
    ) {
        _players.forEach { it.drawAdditionalCards(giveCard, printCards) }
    }
}
