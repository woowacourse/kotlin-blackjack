package controller

import domain.card.CardDeck
import domain.player.Player
import view.InputView
import view.OutputView
import view.YesOrNo

class GameController {

    private val cardDeck = CardDeck()

    fun play() {
        val players = initPlayers()
        val dealer = Player("딜러", 0)

        giveInitialCards(players, dealer)

        // TODO :: 사용자 블랙젝 확인
        players.forEach {
            while(InputView.askDrawMore(it) == YesOrNo.YES){
                it.receive(cardDeck.draw())
                OutputView.printCards(it)
            }
        }

        // TODO :: 딜러 추가 카드 지급
//        printResult()
    }

    private fun initPlayers(): List<Player> {
        val names: List<String> = InputView.inputPlayerNames()

        return names.map { Player(it, InputView.inputBettingMoney(it)) }
    }

    private fun giveInitialCards(players: List<Player>, dealer: Player) {
        players.forEach {
            it.receive(cardDeck.draw())
            it.receive(cardDeck.draw())
        }

        dealer.receive(cardDeck.draw())
        dealer.receive(cardDeck.draw())
    }
}