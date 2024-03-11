package blackjack.model

class Players(
    private val names: List<String>,
    private val readContinueInput: (String) -> String,
) {
    private val _players: Sequence<Player> = names.asSequence()
        .map { name -> Player(GameInfo(name)) { readContinueInput(name) } }

    val players: List<Player> = _players.toList().take(names.size)

    init {
        require(names.distinct().size == names.size) {
            EXCEPTION_DUPLICATED_PLAYERS
        }
        require(names.size in PLAYER_NUM_RANGE) {
            EXCEPTION_NUMBER_OF_PLAYERS.format(names.size)
        }
    }

    fun initializeCards(generateCard: () -> Card) {
        players.forEach { player ->
            player.initializeCards { generateCard() }
        }
    }

    fun getPlayersGameInfo(): List<GameInfo> = players.map { it.gameInfo }

    companion object {
        private const val MINIMUM_NUMBER_OF_PLAYERS = 2
        private const val MAXIMUM_NUMBER_OF_PLAYERS = 8
        private val PLAYER_NUM_RANGE = MINIMUM_NUMBER_OF_PLAYERS..MAXIMUM_NUMBER_OF_PLAYERS
        private const val EXCEPTION_NUMBER_OF_PLAYERS =
            "플레이어의 수로 %d를 입력했습니다. 플레이어 수는 ${MINIMUM_NUMBER_OF_PLAYERS}부터 ${MAXIMUM_NUMBER_OF_PLAYERS}까지 가능합니다."
        private const val EXCEPTION_DUPLICATED_PLAYERS =
            "중복된 플레이어의 이름을 입력하셨습니다."
    }
}
