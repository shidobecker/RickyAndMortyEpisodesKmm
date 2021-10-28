package br.com.shido.rickyandmortyepisodeskmm.android.episode_list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import br.com.shido.rickyandmortyepisodeskmm.android.R
import br.com.shido.rickyandmortyepisodeskmm.android.components.CardContainer
import br.com.shido.rickyandmortyepisodeskmm.android.components.ShimmerEpisodeCardItem
import br.com.shido.rickyandmortyepisodeskmm.android.episode_list.viewmodel.EpisodeListViewModel
import br.com.shido.rickyandmortyepisodeskmm.android.extensions.onLoading
import br.com.shido.rickyandmortyepisodeskmm.android.extensions.onSuccess
import br.com.shido.rickyandmortyepisodeskmm.model.Episode
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class EpisodeListFragment : Fragment() {

    private val viewModel by sharedViewModel<EpisodeListViewModel>()

    @ExperimentalUnitApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                EpisodeListFragmentScreen()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchEpisodes()
    }

    @ExperimentalUnitApi
    @Composable
    fun EpisodeListFragmentScreen() {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.DarkGray),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ricky),
                        contentDescription = null
                    )

                    Text(
                        color = Color.White,
                        fontSize = regularText,
                        fontWeight = FontWeight.Bold,
                        text = "Episode Guide",
                        modifier = Modifier.align(Alignment.End)
                    )


                }
            }

            viewModel.episodesList.onLoading {
                item {
                    for (i in 1..10) {
                        ShimmerEpisodeCardItem(imageHeight = 100.dp)
                    }
                }
            }

            viewModel.episodesList.onSuccess { list ->
                items(items = list) {
                    EpisodeList(episode = it)
                }
            }
        }

    }

    @ExperimentalUnitApi
    @Composable
    fun EpisodeList(episode: Episode) {
        CardContainer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            backgroundColor = Color.Black
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    color = Color.White,
                    text = episode.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = bigText
                )
                Text(color = Color.LightGray, text = episode.episode)
                Text(color = Color.LightGray, text = "Air Date: ${episode.airDate}")
            }
        }
    }
}

@ExperimentalUnitApi
val bigText = TextUnit(20f, TextUnitType.Sp)
@ExperimentalUnitApi
val regularText = TextUnit(16f, TextUnitType.Sp)

