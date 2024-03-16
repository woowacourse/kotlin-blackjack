package blackjack.model.participant

class Players private constructor(val playerGroup: List<Player>) {
    fun profit(dealer: Dealer): Map<Player, Amount> {
        return playerGroup.associateWith { it.profit(dealer) }
    }

    companion object {
        private const val MIN_SIZE = 2
        private const val MAX_SIZE = 8
        private val SIZE_RANGE = MIN_SIZE..MAX_SIZE
        private const val INVALID_SIZE = "플레이어명은 중복될 수 없습니다."
        private const val INVALID_DUPLICATION = "플레이어명은 중복될 수 없습니다."
        private const val INVALID_RANGE = "플레이어는 $MIN_SIZE~$MAX_SIZE 사이여야 합니다."

        fun from(
            playersName: List<String>,
            playersAmount: List<Int>,
        ): Players {
            require(playersName.size == playersAmount.size) { INVALID_SIZE }
            require(playersName.size == playersName.distinct().size) { INVALID_DUPLICATION }
            require(playersName.size in SIZE_RANGE) { INVALID_RANGE }
            return Players(playersName.mapIndexed { idx, name -> Player(name, playersAmount[idx]) })
        }
    }
}
