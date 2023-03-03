package domain

@JvmInline
value class Name(val name: String) {
    init {
        check(name.matches(regex)) { ERROR_NAME_FORMAT }
    }

    companion object {
        private val regex = Regex("^[a-zA-Z가-힣]{1,10}\$")
        private const val ERROR_NAME_FORMAT = "[ERROR] 이름은 문자열만 허용합니다."
    }
}
