package controller

import domain.CardDrawer
import domain.Dealer
import domain.GameResult
import domain.Names
import domain.Participant
import domain.Participants
import domain.Player
import domain.Players
import domain.RandomCardPicker

class BlackJackGame(names: Names, private val cardDrawer: CardDrawer = RandomCardPicker()) {
    val participants: Participants
    private val players: Players
        get() = participants.players
    private val dealer: Dealer
        get() = participants.dealer

    init {
        val players = names.values.map { Player(it, cardDrawer.drawInitCards()) }
        val dealer = Dealer(cardDrawer.drawInitCards())
        participants = Participants(Players(players), dealer)
    }

    private fun Participant.addCard() {
        this.cards.add(cardDrawer.draw())
    }

    private fun Player.playerSelectAdd(getChoiceOfAddCard: (Player) -> Boolean, showPlayerCards: (Player) -> Unit) {
        val isGetCard = getChoiceOfAddCard(this)
        if (isGetCard) {
            addCard()
        }
        if (!isPossibleDrawCard()) return
        showPlayerCards(this)
        if (isGetCard) playerSelectAdd(getChoiceOfAddCard, showPlayerCards)
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
}
