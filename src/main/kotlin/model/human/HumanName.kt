package model.human

data class HumanName(val name: String) {
    init {
        require(name.length in 1..10) { ERROR_INVALID_LENGTH }
    }

    companion object {
        const val ERROR_INVALID_LENGTH = "이름의 길이는 1 이상 10 이하 이어야 합니다."
    }
}
