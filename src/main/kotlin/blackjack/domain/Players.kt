package blackjack.domain

import blackjack.domain.state.Hit
import blackjack.domain.state.Ready

class Players {
    private var _players: List<Player> = listOf()
    val players: List<Player>
        get() = _players

    fun getPlayerNames(
        names: List<String>,
        getMoreCard: (String) -> Boolean,
    ) {
        _players = names.map { Player(it, Ready(Hand(emptyList(), 0)), getMoreCard) }
    }

    fun getBattingAmounts(battingAmount: (String) -> Int) {
        _players =
            players.map { player ->
                player.copy(state = Ready(Hand(listOf(), battingAmount(player.name))))
            }
    }

    fun initializePlayersCard(giveCard: () -> Card) {
        repeat(2) {
            _players =
                players.map {
                    it.copy(state = it.state.draw(giveCard()))
                }
        }
    }

    fun drawMoreCardsPlayers(
        giveCard: () -> Card,
        askDrawCard: (String) -> String,
        printCards: (String, Hand) -> Unit,
    ) {
        _players =
            players.map { player ->
                var newState = player.state

                while (newState is Hit && askDrawCard(player.name).lowercase() == "y") {
                    newState = newState.draw(giveCard())
                    printCards(player.name, newState.hand)
                }

                player.copy(state = newState)
            }
    }
}
