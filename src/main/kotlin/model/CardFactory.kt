package model

import entity.card.Cards

interface CardFactory {
    fun generate(count: Int): Cards
}
