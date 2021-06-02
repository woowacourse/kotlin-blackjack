package controller

import view.InputView

class GameController(val inputView: InputView) {

    fun playGame() {
        inputView.guideGameStart()
    }
}