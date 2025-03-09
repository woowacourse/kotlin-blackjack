package blackjack.domain

import blackjack.domain.card.CardFactoryImpl
import blackjack.domain.card.FakeCardFactory
import blackjack.domain.card.Shape
import blackjack.domain.card.Tier
import blackjack.domain.card.TrumpCard
import blackjack.domain.participant.Participants
import blackjack.fixture.participantsFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class BlackJackGameTest {
    private lateinit var game: BlackJackGame
    private lateinit var participants: Participants

    @BeforeEach
    fun setUp() {
        participants = participantsFixture()
        val deck = Deck(CardFactoryImpl())

        game = BlackJackGame(participants, deck)
    }

    @Test
    fun `게임을 시작하면 각 플레이어와 딜러는 2장의 카드를 지급받는다`() {
        game.handOutInitializedCards(2)
        assertThat(participants.players.first().cards.size()).isEqualTo(2)
    }

    @Test
    fun `플레이어가 hit을 선택하면 카드를 한 장 추가한다`() {
        val player = participants.players.first()

        player.addCard(TrumpCard(Tier.JACK, Shape.DIA))
        player.addCard(TrumpCard(Tier.KING, Shape.DIA))

        game.playGame(
            getPlayerChoice = { UserChoice.from("y") },
            onPlayerStateUpdated = {},
        )

        assertThat(player.cards.size()).isEqualTo(3)
    }

    @Test
    fun `플레이어가 stay를 선택하면 카드의 장수가 유지된다`() {
        game.playGame(
            getPlayerChoice = { UserChoice.from("n") },
            onPlayerStateUpdated = {},
        )
        assertThat(participants.players.first().cards.size()).isEqualTo(0)
    }

    @MethodSource("dealerCardDrawTestSet")
    @ParameterizedTest
    fun `딜러는 카드 점수의 최소합이 16이될 때 까지 카드를 뽑는다`(cards: List<TrumpCard>) {
        val deck = Deck(FakeCardFactory(cards))
        val game = BlackJackGame(participants, deck)

        game.handOutInitializedCards()
        val result = game.processDealerTurn()

        assertEquals(result, 2)
    }

    companion object {
        @JvmStatic
        fun dealerCardDrawTestSet() =
            listOf(
                Arguments.of(
                    listOf(
                        // 플레이어 카드
                        TrumpCard(Tier.KING, Shape.HEART),
                        TrumpCard(Tier.JACK, Shape.HEART),
                        TrumpCard(Tier.TEN, Shape.DIA),
                        TrumpCard(Tier.NINE, Shape.DIA),
                        // 딜러 초기 카드 1
                        TrumpCard(Tier.TWO, Shape.DIA),
                        // 딜러 초기 카드 2
                        TrumpCard(Tier.THREE, Shape.DIA),
                        // 추가 카드 1 (점수 합이 16 미만일 경우)
                        TrumpCard(Tier.SIX, Shape.DIA),
                        // 추가 카드 2 (점수 합이 16 미만일 경우)
                        TrumpCard(Tier.SEVEN, Shape.DIA),
                        TrumpCard(Tier.SEVEN, Shape.HEART),
                    ),
                ),
            )
    }
}
