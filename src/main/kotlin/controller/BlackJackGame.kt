package controller

import domain.BettingMoney
import domain.CardPicker
import domain.Cards
import domain.Dealer
import domain.GameResult
import domain.Name
import domain.Names
import domain.Participant
import domain.Participants
import domain.Player
import domain.Players
import domain.RandomCardPicker

class BlackJackGame(
    names: () -> Names,
    bettingMoney: (Name) -> BettingMoney,
    private val cardPicker: CardPicker = RandomCardPicker(),
) {
    val participants: Participants
    private val players: Players
        get() = participants.players
    private val dealer: Dealer
        get() = participants.dealer

    init {
        val players = initPlayers(names, bettingMoney)
        val dealer = Dealer(drawInitCards())
        participants = Participants(players, dealer)
    }

    private fun initPlayers(names: () -> Names, bettingMoney: (Name) -> BettingMoney): Players {
        return Players(names().values.map { name -> Player(name, drawInitCards(), bettingMoney(name)) })
    }

    private fun drawInitCards(): Cards {
        return Cards(List(INIT_DRAW_CARDS_COUNT) { cardPicker.draw() })
    }

    private fun Participant.addCard() {
        this.cards.add(cardPicker.draw())
    }

    private fun Player.playerSelectAdd(isGetCard: (Player) -> Boolean, showPlayerCards: (Player) -> Unit) {
        if (!isGetCard(this)) {
            showPlayerCards(this)
            return
        }
        do {
            addCard()
            showPlayerCards(this)
        } while (isPossibleDrawCard() && isGetCard(this))
    }

    fun playersSelectAddPhase(isGetCard: (Player) -> Boolean, showPlayerCards: (Player) -> Unit) {
        players.list.forEach { player ->
            player.playerSelectAdd(isGetCard, showPlayerCards)
        }
    }

    fun dealerSelectPhase(result: (Dealer) -> Unit) {
        if (participants.dealer.isPossibleDrawCard()) {
            result(dealer)
            participants.dealer.addCard()
        }
    }

    fun getGameResult(): GameResult {
        return GameResult(participants)
    }

    companion object {
        private const val INIT_DRAW_CARDS_COUNT = 2
    }
}
