package blackjack.model.participant

import blackjack.model.deck.Deck

class Players(names: Set<String>, deck: Deck) {
    val gamePlayers: List<Player> = names.map { Player(it, deck) }

    init {
        require(names.size in MIN_PLAYER_COUNT..MAX_PLAYER_COUNT) { "${MIN_PLAYER_COUNT}명 이상 ${MAX_PLAYER_COUNT}명 이하의 플레이어만 가능합니다." }
        require(names.size == names.toSet().size) { "중복된 이름은 플레이어로 등록할 수 없습니다." }
    }

    companion object {
        private const val MIN_PLAYER_COUNT = 1
        private const val MAX_PLAYER_COUNT = 6

        fun playerNamesOf(
            names: List<String>,
            deck: Deck,
        ): Players {
            validateDuplicateNames(names)
            return Players(names.toSet(), deck)
        }

        private fun validateDuplicateNames(numbers: List<String>) {
            require(numbers.distinct().size == numbers.size) {
                "중복된 이름은 플레이어로 등록할 수 없습니다."
            }
        }
    }
}
