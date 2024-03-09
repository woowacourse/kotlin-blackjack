package blackjack.model

data class Name(private val name: String) {
    init {
        require(name.isNotEmpty()) { EMPTY_NAME_ERROR_MESSAGE }
    }

    companion object {
        private const val EMPTY_NAME_ERROR_MESSAGE = "[ERROR] 이름은 공백이 될 수 없습니다."
    }
}