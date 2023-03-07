package blackjack.view

object InputView {
    fun readStrings(): List<String>? {
        return readln().split(",").takeIf { it.areNotContainBlank() }
    }

    private fun List<String>.areNotContainBlank(): Boolean = this.all { it.isNotBlank() }

    fun readCharacter(): String? {
        return readlnOrNull()
    }
}
