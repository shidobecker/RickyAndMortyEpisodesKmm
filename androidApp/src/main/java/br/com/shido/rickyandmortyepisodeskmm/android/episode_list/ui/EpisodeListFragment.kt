package br.com.shido.rickyandmortyepisodeskmm.android.episode_list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import br.com.shido.rickyandmortyepisodeskmm.android.components.CardContainer
import br.com.shido.rickyandmortyepisodeskmm.android.components.ShimmerEpisodeCardItem
import br.com.shido.rickyandmortyepisodeskmm.android.episode_list.viewmodel.EpisodeListViewModel
import br.com.shido.rickyandmortyepisodeskmm.android.extensions.onLoading
import br.com.shido.rickyandmortyepisodeskmm.android.extensions.onSuccess
import br.com.shido.rickyandmortyepisodeskmm.model.Episode
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class EpisodeListFragment : Fragment() {

    private val viewModel by sharedViewModel<EpisodeListViewModel>()

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

    @Composable
    fun EpisodeListFragmentScreen() {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    Text("Ricky and Morty Episode Guide")
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

    @Composable
    fun EpisodeList(episode: Episode) {
        CardContainer {
            Column() {
                Text(color = Color.Black, text = episode.name)
            }
        }
    }
}

