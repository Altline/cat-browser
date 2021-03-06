package view.breeds

import isNearScrollEnd
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.launch
import lodash
import model.Breed
import react.fc
import react.useEffect
import react.useState
import styled.css
import styled.styledDiv
import view.ApplicationProps
import view.loadingSign
import view.styles.AppStyles
import view.styles.BreedStyles

external interface BreedsPageProps : ApplicationProps

val BreedsPage = fc<BreedsPageProps> { props ->
    var breeds: Array<Breed> by useState(emptyArray())
    var currentPage: Int by useState(0)
    var loading: Boolean by useState(false)
    var reachedPageEnd: Boolean by useState(false)

    useEffect {
        if (breeds.isEmpty()) {
            props.coroutineScope.launch {
                breeds = props.catService.getBreeds(0)
            }
            currentPage = 0
        }

        window.onscroll = lodash.debounce({
            if (!reachedPageEnd && !loading && document.isNearScrollEnd()) {
                props.coroutineScope.launch {
                    loading = true

                    val nextPage = currentPage + 1
                    val newBreeds = props.catService.getBreeds(nextPage)

                    reachedPageEnd = newBreeds.isEmpty()
                    currentPage = nextPage
                    breeds = arrayOf(*breeds, *newBreeds)
                    loading = false
                }
            }
        }, 200)

        cleanup {
            window.onscroll = null
        }
    }

    if (breeds.isEmpty()) {
        loadingSign()
        return@fc
    }

    styledDiv {
        css { +AppStyles.pageTitle }
        +"Breeds"
    }
    styledDiv {
        css { +BreedStyles.breedTileHolder }

        for (breed in breeds) {
            BreedTile {
                attrs.breed = breed
            }
        }
    }

    loadingSign(loading)
}