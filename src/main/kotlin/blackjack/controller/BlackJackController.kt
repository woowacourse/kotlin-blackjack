package blackjack.controller

import blackjack.domain.BlackJack
import blackjack.domain.BlackJackBuilder
import blackjack.domain.RandomCardGenerator
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController {
    private lateinit var blackJack: BlackJack

    fun start() {
        initBlackJack()
        setUpCard()
    }

    private fun initBlackJack() {
        blackJack = BlackJackBuilder {
            cardDeck(RandomCardGenerator())
            participants {
                dealer()
                players(InputView.inputNames())
            }
        }
    }

    private fun setUpCard() {
        drawInitialCards(blackJack)
        OutputView.printInitialHands(blackJack.getInitialHands())
    }

    private fun drawInitialCards(blackJack: BlackJack) {
        blackJack.drawAll()
        blackJack.drawAll()
    }
}
