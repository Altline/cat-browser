package view.styles

import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
import kotlinx.css.properties.scale
import kotlinx.css.properties.transform
import styled.StyleSheet

object BreedStyles : StyleSheet("BreedStyles", isStatic = true) {

    val breedTileHolder by css {
        padding(20.px, 40.px)
        display = Display.grid
        gridTemplateColumns = GridTemplateColumns(GridAutoRows.auto, GridAutoRows.auto, GridAutoRows.auto)
        backgroundColor = Color.lightGray
        borderRadius = 8.px

        descendants("a") {
            textDecoration = TextDecoration.none
        }
    }

    val breedTile by css {
        margin(5.px)
        borderRadius = 3.px
        backgroundColor = Color.white
        display = Display.grid
        gridTemplateRows = GridTemplateRows(GridAutoRows.auto, GridAutoRows.auto)
        color = Color.initial

        hover {
            backgroundColor = Color("#ffe")
            transform {
                scale(1.02)
            }
        }

        child("img") {
            borderTopLeftRadius = 3.px
            borderTopRightRadius = 3.px
            alignSelf = Align.center
        }
        child("div") {
            margin(vertical = 10.px)
            alignSelf = Align.selfEnd
            textAlign = TextAlign.center
            fontSize = 1.2.em
        }
    }

    val breedHead by css {
        margin(bottom = 15.px)
        padding(15.px)
        display = Display.flex
        flexDirection = FlexDirection.column
        alignItems = Align.center
        borderRadius = 5.px

        child("img") {
            +AppStyles.headImage
        }
        child("h1") {
            +AppStyles.pageTitle
        }
        child("h2") {
            +AppStyles.pageSubtitle
            margin(vertical = -10.px)
        }
    }

    val descriptionPanel by css {
        fontSize = 1.06.em
        letterSpacing = 0.0125.em
    }

    val traitPanel by css {
        margin(bottom = 20.px)
        display = Display.grid
        gridTemplateColumns = GridTemplateColumns("auto auto auto")
        columnGap = 32.px
    }
}