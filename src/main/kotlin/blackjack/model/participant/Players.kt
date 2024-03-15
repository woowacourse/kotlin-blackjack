package blackjack.model.participant

import blackjack.model.deck.Deck

class Players(private val names: Set<String>, deck: Deck) {
    val gamePlayers: List<Player> = names.map { Player(it, deck) }

    init {
        require(names.size in MIN_PLAYER_COUNT..MAX_PLAYER_COUNT) { ERROR_PLAYER_COUNT }
    }

    companion object {
        private const val MIN_PLAYER_COUNT = 1
        private const val MAX_PLAYER_COUNT = 6
        private const val ERROR_PLAYER_COUNT = "플레이어는 ${MIN_PLAYER_COUNT}명 이상 ${MAX_PLAYER_COUNT}명 이하만 가능합니다."
        private const val ERROR_DUPLICATE_PLAYER = "중복된 이름은 플레이어로 등록할 수 없습니다."

        fun playerNamesOf(
            names: List<String>,
            deck: Deck,
        ): Players {
            validateDuplicateNames(names)
            return Players(names.toSet(), deck)
        }

        private fun validateDuplicateNames(numbers: List<String>) {
            require(numbers.distinct().size == numbers.size) {
                ERROR_DUPLICATE_PLAYER
            }
        }
    }
}
