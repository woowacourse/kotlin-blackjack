import domain.card.CardDeck
import domain.player.Player
import view.InputView

class MainApplication {}

fun main() {

    val playerNames = InputView.inputPlayerNames()

    val players = playerNames
        .map{ Player(it, InputView.inputBettingMoney(it)) }

    InputView.askDrawMore(players[0])

    val cardDeck = CardDeck()
    cardDeck.draw()
}