package blackjack

import blackjack.model.domain.Hand
import blackjack.model.entitiy.Card
import blackjack.model.state.Hit
import blackjack.model.state.Ready

class Players {
    private var _value: List<Player> = listOf()
    val value: List<Player>
        get() = _value

    fun readNames(names: List<String>, onInputDecision: (String) -> String) {
        _value = names.map { Player(it, onInputDecision) }
    }

    fun readBatingAmounts(onBatingAmount: (String) -> Int) {
        _value = value.map {
            it.copy(state = Ready(Hand(setOf(), onBatingAmount(it.name))))
        }
    }

    fun initPlayersCard(generateCard: () -> Card) {
        repeat(2) {
            _value = value.map {
                it.copy(state = it.state.draw(generateCard()))
            }
        }
    }

    fun drawUntilPlayersSatisfaction(
        drawCard: () -> Card,
        onPrintCards: (String, Hand) -> Unit,
    ) {
        _value = value.map { player ->
            var newState = player.state

            while (newState == Hit(newState.hand) && player.onInputDecision(player.name) == "y") {
                newState = newState.draw(drawCard())
                onPrintCards(player.name, newState.hand)
            }

            player.copy(state = newState)
        }
    }
}
