package br.com.shido.rickyandmortyepisodeskmm.android.episode_list.ui

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.shido.rickyandmortyepisodeskmm.android.R
import br.com.shido.rickyandmortyepisodeskmm.android.components.CardContainer
import br.com.shido.rickyandmortyepisodeskmm.android.components.ShimmerEpisodeCardItem
import br.com.shido.rickyandmortyepisodeskmm.android.components.bigText
import br.com.shido.rickyandmortyepisodeskmm.android.components.regularText
import br.com.shido.rickyandmortyepisodeskmm.android.episode_detail.ui.EpisodeDetailFragment
import br.com.shido.rickyandmortyepisodeskmm.android.episode_list.viewmodel.EpisodeListViewModel
import br.com.shido.rickyandmortyepisodeskmm.episodes_list.events.EpisodeListEvents
import br.com.shido.rickyandmortyepisodeskmm.episodes_list.model.Episode
import br.com.shido.rickyandmortyepisodeskmm.episodes_list.model.EpisodeListState
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
                EpisodeListFragmentScreen(viewModel.episodesState.value)
            }
        }
    }

    private fun setTransparentStatusBar() {
        requireActivity().window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = android.graphics.Color.BLACK
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTransparentStatusBar()
        viewModel.onTriggerEvent(EpisodeListEvents.LoadEpisodes)
    }


    @ExperimentalUnitApi
    @Composable
    fun EpisodeListFragmentScreen(state: EpisodeListState) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Black,
                            Color.DarkGray,
                            Color.LightGray,
                            Color.Gray
                        )
                    )
                ),
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

            if (state.isLoading) {
                item {
                    for (i in 1..10) {
                        ShimmerEpisodeCardItem(imageHeight = 100.dp)
                    }
                }
            }

            if (state.episodeList.isNotEmpty() && state.isLoading.not()) {
                itemsIndexed(items = state.episodeList) { index, item ->
                    if (viewModel.shouldLoadMoreItems(index)) {
                        viewModel.onTriggerEvent(EpisodeListEvents.NextPage)
                    }
                    EpisodeList(episode = item)
                }
            }

        }

    }

    private fun navigateToDetail(episodeId: String, episodeImage: String) {
        val args = Bundle()
        args.putString(EpisodeDetailFragment.EPISODE_ID_EXTRA, episodeId)
        args.putString(EpisodeDetailFragment.EPISODE_IMAGE_EXTRA, episodeImage)
        findNavController().navigate(R.id.action_episodeListFragment_to_episodeDetailFragment, args)
    }

    @ExperimentalUnitApi
    @Composable
    fun EpisodeList(episode: Episode) {
        CardContainer(
            modifier = Modifier
                .clickable {
                    navigateToDetail(episode.id, episode.imageName)
                }
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            backgroundColor = Color.LightGray
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.LightGray)
            ) {
                Image(
                    bitmap = getImageByName(episode.imageName),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(color = Color.Transparent)
                        .clip(RoundedCornerShape(6.dp))
                )

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

    private fun getImageByName(name: String?): ImageBitmap {
        val resourceId: Int = requireContext().resources
            .getIdentifier(name, "drawable", requireContext().packageName)
        val drawable = requireContext().resources.getDrawable(resourceId)
        return (drawable as BitmapDrawable).bitmap.asImageBitmap()
    }

}



