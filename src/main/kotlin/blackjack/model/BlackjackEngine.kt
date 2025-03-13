package blackjack.model

import blackjack.model.WinningResult.BLACKJACK
import blackjack.model.WinningResult.WIN
import blackjack.model.WinningResult.LOSE
import blackjack.model.WinningResult.PUSH

class BlackjackEngine(
    val cardDeck: CardDeck = CardDeck()
) {
    var bets: MutableMap<Player, Amount> = mutableMapOf()

    fun getPlayersBet(players: Players, eventProvider: EventProvider) {
        players.getPlayers().forEach { player ->
            bets[player] = Amount(eventProvider.getBetAmount(player.name).toDouble())
        }
    }

    fun preparePlayers(names: List<String>): Players {
        return Players(names.map { name ->
            Player(
                name,
                makeFirstHand()
            )
        })
    }

    fun prepareDealer(): Dealer = Dealer(hand = makeFirstHand())

    fun drawPlayer(player: Player): Unit = player.draw(cardDeck)

    fun drawDealer(dealer: Dealer): Unit = dealer.drawUntilFinished(cardDeck)

    fun makeFirstHand(): Hand = Hand(List(START_CARD_COUNT) { cardDeck.draw() })

    fun progressPlayersDraw(players: Players, eventListener: EventListener, eventProvider: EventProvider) {
        players.value.forEach { player ->
            eventListener.displayParticipantCards(player.name, player.hand.cards)
        }
        players.value.forEach { player ->
            progressPlayerDrawUntilFinished(player, eventListener, eventProvider)
        }
    }

    fun getDealerMoneyResults(dealer: Dealer, playersEarnMoney: Map<Player, Amount>): Pair<Dealer, Amount> {
        val dealerAmount = Amount(0.0)
        playersEarnMoney.forEach { playerEarnMoney ->
            dealerAmount.addMoney(playerEarnMoney.value.toMinus())
        }
        return Pair(dealer, dealerAmount)
    }

    fun getPlayerMoneyResults(dealer: Dealer, players: Players): Map<Player, Amount> {
        val earnMoney: MutableMap<Player, Amount> = mutableMapOf()
        players.getPlayers().forEach { player ->
            earnMoney[player] = calculateMoney(player, dealer.getPlayerResult(player))
        }
        return earnMoney
    }

    private fun calculateMoney(player: Player, winningResult: WinningResult): Amount {
        val bet = bets[player]
        if (bet == null) throw IllegalArgumentException("해당 유저를 찾을 수 없습니다")
        return when (winningResult) {
            BLACKJACK -> bet.toBlackjackMoney()
            WIN -> bet
            PUSH -> Amount(0.0)
            LOSE -> bet.toMinus()
        }
    }


    private fun progressPlayerDrawUntilFinished(
        player: Player,
        eventListener: EventListener,
        eventProvider: EventProvider,
    ) {
        while (eventProvider.getIsDrawMore(player.name)) {
            drawPlayer(player)
            eventListener.displayParticipantCards(player.name, player.hand.cards)
            if (player.hand.isBust()) return
        }
    }

    fun progressDealerDraw(dealer: Dealer, eventListener: EventListener) {
        drawDealer(dealer)
        eventListener.displayDealerDrawInfo(dealer.name, dealer.getHandSize() - START_CARD_COUNT)
        eventListener.displayParticipantInfo(dealer.name, dealer.hand.cards, dealer.hand.score(), dealer.hand.isBust())
    }

    companion object {
        const val START_CARD_COUNT = 2
    }

}
