package model.domain.state.gameover

import model.domain.card.Card
import model.domain.state.State

abstract class GameOverState() : State {
    override fun draw(card: Card): State {
        throw IllegalStateException("카드를 더 이상 뽑을 수 없습니다.")
    }

    override fun stay(): State {
        throw IllegalStateException("카드를 더 이상 뽑을 수 없습니다.")
    }

    override fun getTotalScore(): Int = hand.getTotalScoreWithAceCard()
}
