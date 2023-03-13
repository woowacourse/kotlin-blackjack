package blackjack.view

interface InputView {
    fun inputNames(): List<String>
    fun inputBettingMoney(name: String): Int
    fun inputDrawCommand(name: String): Boolean
}
