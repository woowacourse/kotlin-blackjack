package blackjack.domain.model

class Game(val deck: Deck, val dealer: Dealer, val players: List<Player>) {
    init {
        val playerNames: List<String> = players.map { player -> player.name }
        require(playerNames == playerNames.distinct()) { MESSAGE_ERROR_PLAYER_NAMES_NOT_UNIQUE }
        require(playerNames.none { playerName -> playerName == dealer.name }) {
            MESSAGE_ERROR_PLAYER_NAME_SAME_AS_DEALER.format(dealer.name)
        }
    }

    fun processPlayerBets(input: (Player) -> Bet) {
        players.forEach { player ->
            player.placeBet(input)
        }
    }

    fun processPlayersHits(
        input: (Player) -> Action,
        output: (Player) -> Unit,
    ) {
        players.forEach { player -> player.processHits(deck, input, output) }
    }

    fun processDealerHits(output: (Dealer) -> Unit) {
        dealer.processHits(deck, output)
    }

    companion object {
        private const val MESSAGE_ERROR_PLAYER_NAMES_NOT_UNIQUE = "플레이어들의 이름은 서로 중복될 수 없습니다."
        private const val MESSAGE_ERROR_PLAYER_NAME_SAME_AS_DEALER = "플레이어의 이름은 딜러의 이름(\"%s\")과 같을 수 없습니다."
    }
}
