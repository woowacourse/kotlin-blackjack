package blackjack.domain.player

@JvmInline
value class PlayerName(val value: String) {

    init {
        require(value.isNotEmpty()) {
            NAME_LENGTH_ERROR
        }
    }

    companion object {
        private const val NAME_LENGTH_ERROR = "[ERROR] 플레이어 이름의 길이는 1 이상이어야 합니다."
    }
}
