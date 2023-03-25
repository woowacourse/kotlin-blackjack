package model.domain.state.gameover

import model.domain.card.Hand

class BlackJack(override val hand: Hand) : GameOverState()
