package blackjack.model.participant

class Players(val playerGroup: List<Player>) {
    companion object {
        private const val MIN_SIZE = 2
        private const val MAX_SIZE = 8
        private val SIZE_RANGE = MIN_SIZE..MAX_SIZE

        private fun invalidDuplicationMessage(duplicatedNames: String) = "플레이어명은 중복될 수 없습니다. 중복된 닉네임: $duplicatedNames"

        private fun invalidRangeMessage(playerSize: Int) = "플레이어는 $MIN_SIZE~$MAX_SIZE 사이여야 합니다. 현재 플레이어 수: $playerSize"

        fun from(playersName: List<String>): Players {
            require(playersName.size == playersName.distinct().size) { invalidDuplicationMessage(findDuplicatedNames(playersName)) }
            require(playersName.size in SIZE_RANGE) { invalidRangeMessage(playersName.size) }
            return Players(playersName.map { Player(PlayerName(it)) })
        }

        private fun findDuplicatedNames(playersName: List<String>): String {
            val duplicatedNames = mutableSetOf<String>()
            return playersName.filter { !duplicatedNames.add(it) }.joinToString()
        }
    }
}
