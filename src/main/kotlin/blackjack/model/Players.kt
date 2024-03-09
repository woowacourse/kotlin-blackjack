package blackjack.model

class Players(val value: List<Player>) {
    init {
        require(value.isNotEmpty()) { }
        initializeCards()
    }

    private fun initializeCards() {
        value.forEach { player ->
            player.initializeCards(CardDeck::pick)
        }
    }

    companion object {
        fun of(
            playerNames: List<String>,
            onInputDecision: (String) -> String,
        ): Players {
            return playerNames
                .map { name ->
                    Player(gameInfo = GameInfo(name), onInputDecision = { onInputDecision(name) })
                }
                .run { Players(this) }
        }
    }
}
