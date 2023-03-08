package controller

import domain.CardPicker
import domain.Cards
import domain.Dealer
import domain.GameResult
import domain.Names
import domain.Participant
import domain.Participants
import domain.Player
import domain.Players
import domain.RandomCardPicker

class BlackJackGame(names: Names, private val cardPicker: CardPicker = RandomCardPicker()) {
    val participants: Participants
    private val players: Players
        get() = participants.players
    private val dealer: Dealer
        get() = participants.dealer

    init {
        val players = names.values.map { Player(it, drawInitCards()) }
        val dealer = Dealer(drawInitCards())
        participants = Participants(Players(players), dealer)
    }

    private fun drawInitCards(): Cards {
        return Cards(List(INIT_DRAW_CARDS_COUNT) { cardPicker.draw() })
    }

    private fun Participant.addCard() {
        this.cards.add(cardPicker.draw())
    }

    private fun Player.playerSelectAdd(getChoiceOfAddCard: (Player) -> Boolean, showPlayerCards: (Player) -> Unit) {
        val isGetCard = getChoiceOfAddCard(this)
        if (isGetCard) {
            addCard()
        }
        if (!isPossibleDrawCard()) return
        showPlayerCards(this)
        if (isGetCard) {
            playerSelectAdd(getChoiceOfAddCard, showPlayerCards)
        }
    }

    fun playersSelectAddPhase(isGetCard: (Player) -> Boolean, showPlayerCards: (Player) -> Unit) {
        players.list.forEach { player -> player.playerSelectAdd(isGetCard, showPlayerCards) }
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
