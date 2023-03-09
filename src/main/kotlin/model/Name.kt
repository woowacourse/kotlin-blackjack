package model

data class Name(val value: String) {
    init {
        require(Regex("([a-z]|[A-Z]|[가-힣])+").matches(value)) { VALIDATE_ERROR }
    }

    companion object {
        const val VALIDATE_ERROR = "이름에는 문자만 들어갈 수 있습니다."
    }
}
