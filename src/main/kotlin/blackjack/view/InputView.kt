package blackjack.view


object InputView {
    fun inputParticipantsNames(): List<String> {
        return readln().split(",").map { it.trim() }
    }
}
