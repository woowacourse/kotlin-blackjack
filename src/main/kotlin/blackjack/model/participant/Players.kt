package blackjack.model.participant

class Players(val playerGroup: List<Player>) {
    init {
        validatePlayers(playerGroup.map { it.name })
    }

    constructor(
        playersName: List<PlayerName>,
        readPlayersBattingAmount: (List<PlayerName>) -> List<BattingAmount>,
    ) : this(players(playersName, readPlayersBattingAmount))

    companion object {
        private const val MIN_SIZE = 2
        private const val MAX_SIZE = 8
        private val SIZE_RANGE = MIN_SIZE..MAX_SIZE

        private fun invalidDuplicationMessage(duplicatedNames: String) = "플레이어명은 중복될 수 없습니다. 중복된 닉네임: $duplicatedNames"

        private fun invalidRangeMessage(playerSize: Int) = "플레이어는 $MIN_SIZE~$MAX_SIZE 사이여야 합니다. 현재 플레이어 수: $playerSize"

        private fun players(
            playersName: List<PlayerName>,
            readPlayersBattingAmount: (List<PlayerName>) -> List<BattingAmount>,
        ): List<Player> {
            validatePlayers(playersName)
            return readPlayersBattingAmount(playersName).mapIndexed { index, battingAmount ->
                Player(playersName[index], battingAmount)
            }
        }

        private fun validatePlayers(playersName: List<PlayerName>) {
            require(playersName.size == playersName.distinct().size) {
                invalidDuplicationMessage(findDuplicatedNames(playersName))
            }
            require(playersName.size in SIZE_RANGE) { invalidRangeMessage(playersName.size) }
        }

        private fun findDuplicatedNames(playersName: List<PlayerName>): String {
            val duplicatedNames = mutableSetOf<String>()
            return playersName.filter { !duplicatedNames.add(it.toString()) }.joinToString()
        }
    }
}
