package domain.game

import domain.card.CardPicker
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
        val dealer = Dealer()
        participants = Participants(players, dealer)
        drawInitCards()
    }

    fun introduceParticipants(showPlayers: (Players) -> Unit, showInitCards: ((Participants) -> Unit)) {
        showPlayers(players)
        showInitCards(participants)
    }

    fun runGame(
        isGetCard: (Player) -> Boolean,
        showPlayerCards: (Player) -> Unit,
        showDealerResult: (Dealer) -> Unit,
    ): GameResult {
        playersSelectAddPhase(isGetCard, showPlayerCards)
        dealerSelectPhase(showDealerResult)
        return GameResult(participants)
    }

    private fun initPlayers(names: () -> Names, bettingMoney: (Name) -> BettingMoney): Players {
        return Players(names().values.map { name -> Player(name, bettingMoney(name)) })
    }

    private fun drawInitCards() {
        participants.list.forEach { participant ->
            repeat(INIT_DRAW_CARDS_COUNT) { participant.addCard() }
        }
    }

    private fun Participant.addCard() {
        draw(cardPicker.draw())
    }

    private fun Player.playerSelectAdd(isGetCard: (Player) -> Boolean, showPlayerCards: (Player) -> Unit) {
        while (isPossibleDrawCard()) {
            if (isGetCard(this)) {
                addCard()
            } else {
                stopDraw()
            }
            showPlayerCards(this)
        }
    }

    private fun playersSelectAddPhase(isGetCard: (Player) -> Boolean, showPlayerCards: (Player) -> Unit) {
        players.list.forEach { player ->
            player.playerSelectAdd(isGetCard, showPlayerCards)
        }
    }

    private fun dealerSelectPhase(showCards: (Dealer) -> Unit) {
        if (participants.dealer.isPossibleDrawCard()) {
            showCards(dealer)
            participants.dealer.addCard()
        }
        dealer.stopDraw()
    }

    companion object {
        private const val INIT_DRAW_CARDS_COUNT = 2
    }
}