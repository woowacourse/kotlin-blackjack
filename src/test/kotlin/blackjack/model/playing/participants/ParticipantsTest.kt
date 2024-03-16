package blackjack.model.playing.participants

import blackjack.model.CLOVER_ACE
import blackjack.model.CLOVER_THREE
import blackjack.model.DIAMOND_ACE
import blackjack.model.DIAMOND_KING
import blackjack.model.HEART_FIVE
import blackjack.model.HEART_QUEEN
import blackjack.model.HEART_SEVEN
import blackjack.model.SPADE_SIX
import blackjack.model.card.RandomDeck
import blackjack.model.playing.cardhand.CardHand
import blackjack.model.playing.participants.player.Player
import blackjack.model.playing.participants.player.PlayerName
import blackjack.model.playing.participants.player.Players
import blackjack.model.winning.DealerWinning
import blackjack.model.winning.PlayersWinning
import blackjack.model.winning.WinningResultStatus
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantsTest {
    private val defaultParticipants =
        Participants(
            Dealer(
                CardHand(
                    CLOVER_ACE,
                    SPADE_SIX,
                ),
            ),
            Players(
                listOf(
                    Player(
                        PlayerName("심지"),
                        CardHand(
                            HEART_FIVE,
                            DIAMOND_KING,
                        ),
                    ),
                    Player(
                        PlayerName("해나"),
                        CardHand(
                            SPADE_SIX,
                            HEART_QUEEN,
                        ),
                    ),
                    Player(
                        PlayerName("악어"),
                        CardHand(
                            HEART_SEVEN,
                            CLOVER_THREE,
                        ),
                    ),
                    Player(
                        PlayerName("팡태"),
                        CardHand(
                            DIAMOND_ACE,
                            SPADE_SIX,
                        ),
                    ),
                ),
            ),
        )

    @Test
    fun `참가자들에게 카드를 2장씩 준다`() {
        val participants =
            Participants(
                Dealer(CardHand(emptyList())),
                Players(
                    listOf(
                        Player(PlayerName("심지"), CardHand(emptyList())),
                        Player(PlayerName("해나"), CardHand(emptyList())),
                    ),
                ),
            )

        participants.addInitialCards(RandomDeck())

        val cardHandSize1 = participants.dealer.cardHand.hand.size
        val cardHandSize2 = participants.players.players[0].cardHand.hand.size
        val cardHandSize3 = participants.players.players[1].cardHand.hand.size

        assertThat(cardHandSize1).isEqualTo(2)
        assertThat(cardHandSize2).isEqualTo(2)
        assertThat(cardHandSize3).isEqualTo(2)
    }

    @Test
    fun `딜러 카드 패의 합과 플레이어들의 카드 패의 합을 각각 비교해서 플레이어들의 승패 여부를 판단한다`() {
        val result =
            PlayersWinning(
                mapOf(
                    PlayerName("심지") to WinningResultStatus.DEFEAT,
                    PlayerName("해나") to WinningResultStatus.DEFEAT,
                    PlayerName("악어") to WinningResultStatus.DEFEAT,
                    PlayerName("팡태") to WinningResultStatus.PUSH,
                ),
            )
        val winningResult = defaultParticipants.getFinalWinning()
        assertThat(winningResult.playerWinning).isEqualTo(result)
    }

    @Test
    fun `딜러의 최종 결과를 가져온다`() {
        val winningResult = defaultParticipants.getFinalWinning()
        val expectedDealerWinning = DealerWinning(3, 0, 1)

        assertThat(winningResult.dealerWinning).isEqualTo(expectedDealerWinning)
    }
}
