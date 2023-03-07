package blackjack.domain

import blackjack.domain.dealer.Dealer
import blackjack.domain.dealer.DrawResult
import blackjack.domain.gameResult.TotalGameResult
import blackjack.domain.player.Player

class BlackJackGame(
    val dealer: Dealer = Dealer(),
    private val blackJackReferee: BlackJackReferee = BlackJackReferee(),
) {

    lateinit var players: List<Player>
        private set

    // TODO: mapIndex 맘에 안듦...
    fun initPlayers(
        playerNames: List<String>,
        battingMoneys: List<Int>,
    ) {
        players = playerNames.mapIndexed { playerIndex, playerName ->
            Player(playerName, battingMoneys[playerIndex])
        }
    }

    // TODO: List<Player>가 Controller에 존재하는 것이 아니라 domain에 존재해야 하기 떄문에 메소드를 넘겨 넘겨줘야하는 상황
    fun drawAdditionalCardsForPlayers(
        isPlayerWantedAdditionalCards: (player: Player) -> Boolean,
    ){
        players.forEach { player ->
            drawCardsRepeatedly(player) { isPlayerWantedAdditionalCards(player) }
        }
    }

    // TODO: 카드 뽑고 보여주는 과정은 어떻게? 메소드 넘겨서?
    private fun drawCardsRepeatedly(
        player: Player,
        isPlayerWantedAdditionalCards: (player: Player) -> Boolean,
    ) {
        do {
            val isPlayerWanted = isPlayerWantedAdditionalCards(player)
        } while (isPlayerWanted && player.drawCard())

        player.checkIsDrawnNothing()
    }

    fun drawAdditionalCardsForDealer(): DrawResult {
        val isDealerDrawn = dealer.drawCard()

        return isDealerDrawn
    }

    fun judgeGameResults(): TotalGameResult = blackJackReferee.judgeTotalGameResults(players, dealer)
}
