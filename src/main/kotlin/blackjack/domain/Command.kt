package blackjack.domain

class Command(val value: String) {
    private val validCommands = listOf("y", "n", "Y", "N")

    init {
        require(value in validCommands) { "${value}은(는) 입력 가능한 명령어가 아닙니다." }
    }
}
