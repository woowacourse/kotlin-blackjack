package domain.game

import domain.card.CardPicker
import domain.card.Cards
import domain.card.RandomCardPicker
import domain.participant.BettingMoney
import domain.participant.Dealer
import domain.participant.Name
import domain.participant.Names
import domain.participant.Participant
import domain.participant.Participants
import domain.participant.Player
import domain.participant.Players

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
