package br.com.shido.rickyandmortyepisodeskmm.android.episode_detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import br.com.shido.rickyandmortyepisodeskmm.android.components.CardContainer
import br.com.shido.rickyandmortyepisodeskmm.android.components.bigText
import br.com.shido.rickyandmortyepisodeskmm.android.components.regularText
import br.com.shido.rickyandmortyepisodeskmm.android.episode_detail.viewmodel.EpisodeDetailViewModel
import br.com.shido.rickyandmortyepisodeskmm.android.extensions.getImageByName
import br.com.shido.rickyandmortyepisodeskmm.episodes_list.model.Character
import br.com.shido.rickyandmortyepisodeskmm.episodes_list.model.EpisodeState
import com.skydoves.landscapist.glide.GlideImage
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class EpisodeDetailFragment : Fragment() {

    private val viewModel by sharedViewModel<EpisodeDetailViewModel>()

    companion object {
        const val EPISODE_ID_EXTRA = "EPISODE_ID_EXTRA"
        const val EPISODE_IMAGE_EXTRA = "EPISODE_IMAGE_EXTRA"
    }

    @ExperimentalUnitApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                EpisodeDetailScreen2(viewModel.episodeState.value)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val episodeId = arguments?.getString(EPISODE_ID_EXTRA) ?: ""
        val episodeImage = arguments?.getString(EPISODE_IMAGE_EXTRA) ?: ""

        viewModel.setArguments(episodeId, episodeImage)
        viewModel.fetchEpisode()
    }

    @ExperimentalUnitApi
    @Composable
    fun EpisodeDetailScreen(episodeState: EpisodeState) {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Image(
                bitmap = requireContext().getImageByName(viewModel.episodeImage),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(color = Color.Transparent)
            )


            val episode = episodeState.episode

            if (episodeState.isLoading) {
                Text("Loading")
            }


            episode?.let {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.DarkGray),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        color = Color.White,
                        text = episode.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = bigText
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        color = Color.Black,
                        text = episode.episode,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Text(
                        color = Color.Black,
                        text = "Air Date: ${episode.airDate}",
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }


        }
    }


    @ExperimentalUnitApi
    @Composable
    fun EpisodeDetailScreen2(episodeState: EpisodeState) {
        val numberOfItemsByRow = LocalConfiguration.current.screenWidthDp / 200

        LazyColumn(
            Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            val episode = episodeState.episode

            item {
                Image(
                    bitmap = requireContext().getImageByName(viewModel.episodeImage),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(color = Color.Transparent)
                )


                episode?.let {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.DarkGray),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.padding(16.dp),
                            color = Color.White,
                            text = "Summary",
                            fontWeight = FontWeight.Bold,
                            fontSize = bigText
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            color = Color.Black,
                            text = episode.name,
                            modifier = Modifier.padding(top = 4.dp)
                        )

                        Text(
                            color = Color.Black,
                            text = episode.episode,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        Text(
                            color = Color.Black,
                            text = "Air Date: ${episode.airDate}",
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }

            }

            val characters = episode?.characters

            item{
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        color = Color.White,
                        text = "Character List:",
                        fontWeight = FontWeight.Bold,
                        fontSize = bigText
                    )
                }
             }



            characters?.let {
                itemsIndexed(items = it.chunked(numberOfItemsByRow)) { index, rowItems ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(14.dp),
                        modifier = Modifier.padding(horizontal = 16.dp),
                    ) {
                        for (character in rowItems) {
                            CharacterCard(character = character, modifier = Modifier.weight(1F))
                        }
                    }
                    Spacer(Modifier.height(14.dp))

                }
            }

        }
    }


    @ExperimentalUnitApi
    @Composable
    fun CharacterCard(character: Character, modifier: Modifier = Modifier) {
        CardContainer {
            Column(modifier = modifier.width(200.dp).height(200.dp), verticalArrangement = Arrangement.Center) {
                GlideImage(
                    imageModel = character.image, modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth().clip(RoundedCornerShape(6.dp))
                )
                Text(character.name, fontSize = bigText, modifier = Modifier.padding(4.dp))
                Text(character.species, fontSize = regularText, modifier = Modifier.padding(4.dp))
                Text(character.status, fontSize = regularText, modifier = Modifier.padding(4.dp))
            }
        }

    }

}