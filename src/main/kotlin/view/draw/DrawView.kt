package view.draw

import domain.person.Player

object DrawView {

    fun askPlayerDraw(name: String): Boolean {
        return getPlayerDecision(name).doesGetMoreCard
    }

    private fun getPlayerDecision(name: String): DrawInput.Decision {
        DrawOutput.printRequestDecision(name)
        return DrawInput.inputDecision() ?: getPlayerDecision(name)
    }

    fun printPlayerCards(player: Player) {
        DrawOutput.printInitialCards(player)
    }

    fun printDealerDrew() {
        DrawOutput.printDealerOneMoreCard()
        println()
    }

    fun printDealerDidNotDrew() {
        DrawOutput.printDealerNoMoreCard()
        println()
    }
}
