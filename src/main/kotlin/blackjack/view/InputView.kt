package blackjack.view

class InputView {
    fun getPlayers(): List<String> = readln().split(",").map { name -> name.trim() }

    fun getIsDrawMore(): Boolean =
        when (readln().lowercase()) {
            "y" -> true
            "n" -> false
            else -> throw IllegalArgumentException("[ERROR] 올바르지 않은 입력입니다.")
        }
}
