package model

import entity.Cards

interface CardFactory {
    fun generate(count: Int): Cards
}
