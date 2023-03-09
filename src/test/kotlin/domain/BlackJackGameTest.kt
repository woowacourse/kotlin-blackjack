package domain

import domain.card.Card
import domain.card.CardNumber
import domain.card.CardShape
import domain.card.Deck
import domain.person.Dealer
import domain.person.Participants
import domain.person.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BlackJackGameTest {

    lateinit var deck: Deck
    lateinit var participants: Participants
    fun cardNumbersFactory(cardNumbers: List<CardNumber>) = cardNumbers.map { Card(CardShape.HEART, it) }

    @BeforeEach
    fun `초기 세팅`() {
        deck = Deck.create()
        val players = listOf(
            Player(cardNumbersFactory(listOf(CardNumber.KING, CardNumber.QUEEN)), "베르"),
        )
        val dealer = Dealer(cardNumbersFactory(listOf(CardNumber.SIX, CardNumber.KING)))
        participants = Participants(dealer, players)
    }

    @Test
    fun `플레이어들의 카드 합계가 21을 넘지 않을 때 받고 싶으면 한장 더 받는다`() {
        // given
        val blackJackGame = BlackJackGame(deck, participants)
        val continueDefinitions = mutableListOf(true)

        fun getPlayerWantContinue(name: String) = continueDefinitions.removeFirst()

        fun printPlayerCards(player: Player): () -> Unit = {}

        // when
        blackJackGame.handOutCardsToPlayers(::getPlayerWantContinue, ::printPlayerCards)

        // then
        assertThat(participants.players[0].cards.value).hasSize(3)
    }

    @Test
    fun `플레이어들의 카드 합계가 21을 넘지 않을 때 받고 싶지 않으면 그만 받는다`() {
        // given
        val blackJackGame = BlackJackGame(deck, participants)
        val continueDefinitions = mutableListOf(false)

        fun getPlayerWantContinue(name: String) = continueDefinitions.removeFirst()

        fun printPlayerCards(player: Player): () -> Unit = {}

        // when
        blackJackGame.handOutCardsToPlayers(::getPlayerWantContinue, ::printPlayerCards)

        // then
        assertThat(participants.players[0].cards.value).hasSize(2)
    }

    @Test
    fun `플레이어들의 카드 합계가 21을 넘었을 때 무조건 그만 받는다`() {
        // given
        val blackJackGame = BlackJackGame(deck, participants)
        val continueDefinitions = mutableListOf(true)

        fun getPlayerWantContinue(name: String) = continueDefinitions.removeFirst()

        fun printPlayerCards(player: Player): () -> Unit = {}

        participants.players[0].cards.add(Card(CardShape.HEART, CardNumber.QUEEN))

        // when
        blackJackGame.handOutCardsToPlayers(::getPlayerWantContinue, ::printPlayerCards)

        // then
        assertThat(participants.players[0].cards.value).hasSize(3)
    }

    @Test
    fun `딜러의 카드 합계가 16 이면 카드를 한장 더 받는다`() {
        // given
        val blackJackGame = BlackJackGame(deck, participants)

        fun printDealerGetCardOrNot(isDealerGetCard: Boolean): () -> Unit = {}

        // when
        blackJackGame.handOutCardsToDealer(::printDealerGetCardOrNot)

        // then
        assertThat(participants.dealer.cards.value).hasSize(3)
    }
}
