package blackjack.view

class InputView {
    fun getPlayers(): List<String> = readln().split(",").map { name -> name.trim() }

    fun getIsDrawMore(): String = readln().trim()
}
