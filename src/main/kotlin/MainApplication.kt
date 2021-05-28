import domain.Player
import view.InputView

class MainApplication {}

fun main() {

    val playerNames = InputView.inputPlayerNames()

    val players = playerNames
        .map{ Player(it, InputView.inputBettingMoney(it)) }

    InputView.askDrawMore(players[0])
}