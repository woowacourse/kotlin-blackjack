package blackjack.domain.player

import blackjack.domain.card.CardDeck
import blackjack.domain.card.Cards.Companion.CARD_SETTING_COUNT
import blackjack.domain.card.CardsGenerator
import blackjack.domain.card.RandomCardsGenerator
import blackjack.domain.result.GameResult
import blackjack.domain.result.ParticipantProfit

class BlackjackManager(
    cardsGenerator: CardsGenerator = RandomCardsGenerator(),
    playerNames: List<String>,
    getBetAmount: (String) -> Int = { 0 }
) {

    private val cardDeck = CardDeck(cardsGenerator)
    private val dealer = Dealer()
    private val participants: Participants

    init {
        participants = Participants(
            playerNames.map {
                Participant(it, betAmount = getBetAmount(it))
            }
        )
    }

    fun setupCards(onSetUpCards: (Dealer, Participants) -> Unit) {
        repeat(CARD_SETTING_COUNT) { provideCard(dealer) }
        participants.values.forEach { participant ->
            repeat(CARD_SETTING_COUNT) {
                provideCard(participant)
            }
        }
        onSetUpCards(dealer, participants)
    }

    fun playGame(
        requestMoreCard: (String) -> Boolean,
        onProvideParticipantCard: (Participant) -> Unit,
        onProvideDealerCard: () -> Unit
    ) {
        playParticipantsTurns(requestMoreCard, onProvideParticipantCard)
        playDealerTurn(onProvideDealerCard)
    }

    fun calculatePlayersResult(
        onCalculatePlayersScore: (Dealer, Participants) -> Unit,
        onCalculatePlayersProfit: (GameResult) -> Unit
    ) {
        onCalculatePlayersScore(dealer, participants)
        onCalculatePlayersProfit(GameResult(calculateParticipantsProfit()))
    }

    private fun playParticipantsTurns(
        requestMoreCard: (String) -> Boolean,
        onProvideCard: (Participant) -> Unit
    ) {
        participants.values.forEach {
            playParticipantTurn(it, requestMoreCard, onProvideCard)
        }
    }

    private fun playParticipantTurn(
        participant: Participant,
        requestMoreCard: (String) -> Boolean,
        onProvideCard: (Participant) -> Unit
    ) {
        while (true) {
            val check = participant.checkProvideCardPossible()
            if (!check) break
            val answer = requestMoreCard(participant.name)
            if (answer) provideCard(participant)
            onProvideCard(participant)
            if (!answer) break
        }
    }

    private fun playDealerTurn(onProvideCard: () -> Unit) {
        while (true) {
            if (!dealer.checkProvideCardPossible()) break
            provideCard(dealer)
            onProvideCard()
        }
    }

    private fun provideCard(player: Player) {
        cardDeck.apply {
            val card = provide() ?: throw IllegalArgumentException(NO_CARD_MESSAGE)
            player.addCard(card)
        }
    }

    private fun calculateParticipantsProfit(): List<ParticipantProfit> {
        return participants.values.map {
            ParticipantProfit(it, it.calculateEarningRate(dealer))
        }
    }

    companion object {
        private const val NO_CARD_MESSAGE = "[ERROR] 더 이상 발급할 수 있는 카드가 없습니다."
    }
}
