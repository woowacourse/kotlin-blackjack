package domain.participants

class Names(val userNames: List<String>) {
    init {
        require(userNames.size <= 8) { ERROR_UNDER_EIGHT }
        require(checkDuplication()) { ERROR_NOT_DUPLICATION }
    }

    private fun checkDuplication(): Boolean = userNames.size == userNames.distinct().size

    companion object {
        private const val ERROR_UNDER_EIGHT = "[ERROR] 8명 이하만 참여할 수 있습니다"
        private const val ERROR_NOT_DUPLICATION = "[ERROR] 중복된 값을 입력할 수 없습니다"
    }
}
