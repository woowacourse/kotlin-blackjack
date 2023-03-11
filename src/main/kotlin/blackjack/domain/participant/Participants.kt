package blackjack.domain.participant

import blackjack.domain.card.CardDeck
import blackjack.domain.money.Money
import blackjack.domain.result.CardResult
import blackjack.domain.result.MatchResult

class Participants(private val participants: List<Participant>) {
    init {
        require(participants.size in MINIMUM_PARTICIPANTS..MAXIMUM_PARTICIPANTS) {
            "블랙잭은 딜러를 포함하여 최소 ${MINIMUM_PARTICIPANTS}명에서 최대 ${MAXIMUM_PARTICIPANTS}명의 플레이어가 참여 가능합니다. (현재 플레이어수 : ${participants.size}명)"
        }
    }

    fun drawFirst(
        deck: CardDeck,
        onStartFirstDrawn: (Participants) -> Unit = {},
        onFirstDrawn: (Participant) -> Unit = {},
    ): Participants {
        onStartFirstDrawn(this)
        return Participants(
            dealerFirst()
                .map { participant -> participant.draw(deck.draw()).draw(deck.draw()) }
                .onEach(onFirstDrawn)
        )
    }

    fun takeTurns(deck: CardDeck, onDrawn: (Participant) -> Unit): Participants = Participants(
        playersFirst().map { participant -> drawUntilCanDraw(participant, deck, onDrawn) }
    )

    private fun drawUntilCanDraw(
        participant: Participant,
        deck: CardDeck,
        onDrawn: (Participant) -> Unit
    ): Participant {
        var drawnParticipant = participant
        if (!drawnParticipant.canDraw()) return drawnParticipant

        do {
            drawnParticipant = checkPlayerNeedToDraw(drawnParticipant)
            drawnParticipant = drawCard(drawnParticipant, deck, onDrawn)
        } while (drawnParticipant.canDraw())
        return drawnParticipant
    }

    private fun drawCard(
        participant: Participant,
        deck: CardDeck,
        onDrawn: (Participant) -> Unit
    ): Participant {
        var drawnParticipant = participant

        if (participant.canDraw()) {
            drawnParticipant = participant.draw(deck.draw())
            onDrawn(drawnParticipant)
        }
        return drawnParticipant
    }

    private fun checkPlayerNeedToDraw(drawnParticipant: Participant): Participant {
        if (drawnParticipant is Player) return playerNeedToDraw(drawnParticipant)
        return drawnParticipant
    }

    private fun playerNeedToDraw(player: Player): Participant {
        if (player.needToDraw()) return player
        return player.stay()
    }

    fun getMatchResults(): List<MatchResult> = listOf(getDealerMatchResult()) + getPlayerMatchResults()

    private fun getPlayerMatchResults(): List<MatchResult> = getPlayers().map { player ->
        MatchResult(player, player.getProfit(getDealer()))
    }

    private fun getDealerMatchResult(): MatchResult {
        val dealer = getDealer()
        var totalProfit = Money(0)
        getPlayers().forEach { totalProfit += dealer.getProfit(it) }
        return MatchResult(dealer, totalProfit)
    }

    fun getCardResults(): List<CardResult> = dealerFirst().map { participant ->
        CardResult(
            participant,
            participant.getCards(),
            participant.getTotalScore()
        )
    }

    fun getPlayers(): List<Participant> = participants.filterIsInstance<Player>()

    fun getDealer(): Participant = participants.first { it is Dealer }

    private fun dealerFirst(): List<Participant> = listOf(getDealer()) + getPlayers()

    private fun playersFirst(): List<Participant> = getPlayers() + getDealer()

    companion object {
        private const val MINIMUM_PARTICIPANTS = 2
        private const val MAXIMUM_PARTICIPANTS = 8
    }
}
