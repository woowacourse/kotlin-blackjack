package blackjack.domain.model

class Game(
    private val deck: Deck,
    private val dealer: Dealer,
    private val players: List<Player>,
) {
    init {
        val playerNames: List<String> = players.map { player -> player.name }
        require(playerNames == playerNames.distinct()) { MESSAGE_ERROR_PLAYER_NAMES_NOT_UNIQUE }
        require(playerNames.none { playerName -> playerName == dealer.name }) {
            MESSAGE_ERROR_PLAYER_NAME_SAME_AS_DEALER.format(dealer.name)
        }
    }

    fun processBets(input: (Player) -> Bet) {
        players.forEach { player -> player.placeBet(input) }
    }

    fun showInitialStatus(output: (String, List<Card>) -> Unit) {
        output(dealer.name, dealer.showStartingHand())
        players.forEach { player -> output(player.name, player.showHand()) }
    }

    fun showFinalStatus(output: (String, List<Card>, Int) -> Unit) {
        output(dealer.name, dealer.showHand(), dealer.computePoint())
        players.forEach { player -> output(player.name, player.showHand(), player.computePoint()) }
    }

    fun showProfits(output: (String, Int) -> Unit) {
        val playerProfits: Map<Player, Int> = getPlayersProfits()
        val dealerProfit: Int = getDealerProfit(playerProfits)
        output(dealer.name, dealerProfit)
        playerProfits.forEach { (player, profit) ->
            output(player.name, profit)
        }
    }

    fun showInitialDeal(output: (String, List<String>) -> Unit) {
        output(dealer.name, players.map(Player::name))
    }

    fun processPlayersHits(
        input: (Player) -> Action,
        output: (String, List<Card>) -> Unit,
    ) {
        players.forEach { player -> player.processHits(deck, input, output) }
    }

    fun processDealerHits(output: (String, Int) -> Unit) {
        dealer.processHits(deck, output)
    }

    fun getPlayersProfits(): Map<Player, Int> {
        return players.associateWith { player -> player.computeProfitAgainst(dealer) }
    }

    fun getDealerProfit(playersProfits: Map<Player, Int>): Int {
        return -1 * playersProfits.values.sum()
    }

    companion object {
        private const val MESSAGE_ERROR_PLAYER_NAMES_NOT_UNIQUE = "플레이어들의 이름은 서로 중복될 수 없습니다."
        private const val MESSAGE_ERROR_PLAYER_NAME_SAME_AS_DEALER = "플레이어의 이름은 딜러의 이름(\"%s\")과 같을 수 없습니다."
    }
}
