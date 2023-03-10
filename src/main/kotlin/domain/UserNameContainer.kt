package domain

class UserNameContainer(val names: List<String>) {

    init {
        exceptDuplicatedName()
        exceptEmptyName()
        require(names.size <= MAXIMUM_PLAYERS) { ERROR_TOO_MANY_PLAYERS }
    }

    private fun exceptDuplicatedName() {
        require(names.toSet().size == names.size) {
            ERROR_DUPLICATE_USER_NAME
        }
    }

    private fun exceptEmptyName() {
        require(!names.contains(BLANK)) {
            ERROR_EMPTY_USER_NAME
        }
    }

    companion object {
        private const val ERROR_DUPLICATE_USER_NAME = "[ERROR] 유저의 이름이 중복되었습니다."
        private const val ERROR_EMPTY_USER_NAME = "[ERROR] 유저의 이름이 비어있습니다."
        private const val ERROR_TOO_MANY_PLAYERS = "[ERROR] 참가자는 최대 8명입니다."
        private const val BLANK = ""
        private const val MAXIMUM_PLAYERS = 8
    }
}
