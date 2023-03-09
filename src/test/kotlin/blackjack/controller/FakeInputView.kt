package blackjack.controller

import blackjack.view.InputView

class FakeInputView(
    private val names: List<String>,
    private val money: Map<String, Int>,
    private val commands: Map<String, Boolean>
) : InputView {
    override fun inputNames(): List<String> = names

    override fun inputBettingMoney(name: String): Int = money[name] ?: 0

    override fun inputDrawCommand(name: String): Boolean = commands[name] ?: false
}
