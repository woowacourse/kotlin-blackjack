package blackjack.view

class InputView {
    fun readPlayers(): List<String> =
        readln()
            .splitToSequence(",")
            .filter { it.isNotBlank() }
            .map { name -> name.trim() }
            .toList()

    fun readWantToHit(): Boolean {
        val input = readln()
        return input == "y"
    }
}
