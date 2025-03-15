package blackjack.domain.model

import blackjack.domain.model.card.Deck
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Participant
import blackjack.domain.model.participant.Player

class Game(
    private val deck: Deck,
    val dealer: Dealer,
    val players: List<Player>,
) {
    init {
        val playerNames: List<String> = players.map { player -> player.name }
        require(playerNames == playerNames.distinct()) { ERROR_MESSAGE_PLAYER_NAMES_NOT_UNIQUE }
        require(playerNames.none { playerName -> playerName == dealer.name }) {
            ERROR_MESSAGE_PLAYER_NAME_SAME_AS_DEALER.format(dealer.name)
        }
    }

    fun processBets(input: (Player) -> Bet) {
        players.forEach { player -> player.placeBet(input) }
    }

    fun processPlayersHits(
        input: (Player) -> Action,
        output: (Player) -> Unit,
    ) {
        players.forEach { player -> player.processHits(deck, input, output) }
    }

    fun processDealerHits(output: (Dealer, Int) -> Unit) {
        dealer.processHits(deck, output)
    }

    fun showStatus(output: (Participant) -> Unit) {
        output(dealer)
        players.forEach { player -> output(player) }
    }

    companion object {
        private const val ERROR_MESSAGE_PLAYER_NAMES_NOT_UNIQUE = "플레이어들의 이름은 서로 중복될 수 없습니다."
        private const val ERROR_MESSAGE_PLAYER_NAME_SAME_AS_DEALER = "플레이어의 이름은 딜러의 이름(\"%s\")과 같을 수 없습니다."
    }
}
