package blackjack.domain.player

import blackjack.domain.card.CardDeck
import blackjack.domain.card.Cards.Companion.CARD_SETTING_COUNT
import blackjack.domain.card.CardsGenerator
import blackjack.domain.card.RandomCardsGenerator

class BlackjackManager(
    cardsGenerator: CardsGenerator = RandomCardsGenerator(),
    playerNames: List<String>,
    getBetAmount: (String) -> Int = { 0 }
) {

    private val cardDeck = CardDeck(cardsGenerator)
    val dealer = Dealer()
    val participants: Participants

    init {
        participants = Participants(
            playerNames.map {
                Participant(it, betAmount = getBetAmount(it))
            }
        )
    }

    fun setupCards() {
        repeat(CARD_SETTING_COUNT) { provideCard(dealer) }
        participants.values.forEach { participant ->
            repeat(CARD_SETTING_COUNT) {
                provideCard(participant)
            }
        }
    }

    fun playGame(
        requestMoreCard: (String) -> Boolean,
        onProvideParticipantCard: (Participant) -> Unit,
        onProvideDealerCard: () -> Unit
    ) {
        playParticipantsTurns(requestMoreCard, onProvideParticipantCard)
        playDealerTurn(onProvideDealerCard)
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

    private fun calculateParticipantsEarningRate(): ParticipantsEarningRate = ParticipantsEarningRate(
        participants.values.map { participant ->
            participant.calculateEarningRate(dealer)
        }
    )

    fun calculateParticipantsProfit(): ParticipantsProfit {
        val participantsEarningRate = calculateParticipantsEarningRate()
        return ParticipantsProfit(
            participants.values.map { participant ->
                val name = participant.name
                val participantEarningRate: ParticipantEarningRate =
                    participantsEarningRate.values.find { participantEarningRate -> participantEarningRate.name == name }
                        ?: throw IllegalArgumentException(ERROR_CANT_FIND_PARTICIPANT_EARNING_RATE)
                ParticipantProfit(
                    name,
                    (participant.betAmount * participantEarningRate.earningRate.of(participant.isBlackjack)).toInt()
                )
            }
        )
    }

    private fun provideCard(player: Player) {
        cardDeck.apply {
            val card = provide() ?: run {
                println(NO_CARD_MESSAGE)
                return
            }
            player.addCard(card)
        }
    }

    companion object {
        private const val ERROR_CANT_FIND_PARTICIPANT_EARNING_RATE = "[ERRPR] 해당 참가자의 수익률을 찾을 수 없습니다"
        private const val NO_CARD_MESSAGE = "[ERROR] 더 이상 발급할 수 있는 카드가 없습니다."
    }
}
