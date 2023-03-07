package domain.person

enum class Decision(val value: Boolean) {
    YES(true),
    NO(false),
    ;

    companion object {

        fun of(value: Boolean): Decision = values().find { it.value == value }
            ?: throw IllegalArgumentException("Decision에서 발생한 예상치 못한 오류입니다.")
    }
}
