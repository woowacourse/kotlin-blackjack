package model

class GameManager(private val cards: Cards) {
    private lateinit var dealer: Dealer
    private lateinit var players: Players

    fun getDealer(): Dealer = dealer

    fun getPlayers(): Players = players

    fun startGame(playerNames: List<String>) {
        dealer = Dealer(Hand(emptyList()))
        dealer.receiveCards(cards::drawCards)

        players = Players(playerNames.map { Player(it, Hand(emptyList())) })
        players.forEach { it.receiveCards(cards::drawCards) }
    }

    fun dealerPlay(): Int {
        var drawCount = 0
        while (dealer.decideToHit()) {
            dealer.receiveCards(cards::drawCards)
            drawCount++
        }
        return drawCount
    }

    fun playersPlay(
        shouldHit: (Player) -> Boolean,
        showCards: (Player) -> Unit,
    ) {
        players.forEach { player ->
            while (player.decideToHit() && shouldHit(player)) {
                player.receiveCards(cards::drawCards)
                showCards(player)
            }
        }
    }

    fun getGameResult(): GameOutput {
        return GameResultDecider(dealer, players).compareWinOrLose()
    }
}
