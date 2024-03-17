package blackjack.model.participant

import blackjack.model.deck.Deck
import blackjack.model.participant.state.Finish

class Players private constructor(names: Set<String>) {
    val gamePlayers: List<Player> = names.map { Player(it) }

    init {
        require(names.size in MIN_PLAYER_COUNT..MAX_PLAYER_COUNT) { "${MIN_PLAYER_COUNT}명 이상 ${MAX_PLAYER_COUNT}명 이하의 플레이어만 가능합니다." }
    }

    private fun initPlayersCard(deck: Deck) {
        gamePlayers.forEach { player ->
            player.initCards(deck.draw(INIT_CARD_AMOUNT))
        }
    }

    fun playTurn(
        deck: Deck,
        isHit: (String) -> Boolean,
        showResult: (Player) -> Unit,
    ): Map<Player, Finish> {
        val gameResults = mutableMapOf<Player, Finish>()
        gamePlayers.forEach { player ->
            val result = player.playTurn(deck::draw, isHit, showResult)
            gameResults[player] = result
        }
        return gameResults
    }

    companion object {
        private const val MIN_PLAYER_COUNT = 1
        private const val MAX_PLAYER_COUNT = 6
        private const val INIT_CARD_AMOUNT = 2

        fun withInitCards(
            names: List<String>,
            deck: Deck,
        ): Players {
            validateDuplicateNames(names)
            return Players(names.toSet()).also { it.initPlayersCard(deck) }
        }

        private fun validateDuplicateNames(numbers: List<String>) {
            require(numbers.distinct().size == numbers.size) {
                "중복된 이름은 플레이어로 등록할 수 없습니다."
            }
        }
    }
}
