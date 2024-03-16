package blackjack.model

data class ParticipantName(private val name: String) {
    init {
        require(name.isNotEmpty()) { ERROR_MESSAGE_EMPTY_NAME }
    }

    override fun toString(): String = name

    companion object {
        private const val ERROR_MESSAGE_EMPTY_NAME = "[ERROR] 이름은 공백이 될 수 없습니다."
    }
}
