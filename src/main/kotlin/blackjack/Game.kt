package blackjack

import blackjack.domain.Player
import blackjack.view.InputView

fun main() {
    val playerNames = InputView.getNames()
    val players = playerNames.map(::Player)
}
