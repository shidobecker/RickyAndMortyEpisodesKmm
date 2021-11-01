//
//  EpisodeListScreen.swift
//  iosApp
//
//  Created by Julio Ribeiro on 28/10/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct EpisodeListScreen: View {
    
    //dependencies
    private let episodesUseCase : EpisodeListUseCase
    
    @ObservedObject var viewModel: EpisodesListViewModel

    
    init(episodesUseCase: EpisodeListUseCase){
        self.episodesUseCase = episodesUseCase
        self.viewModel = EpisodesListViewModel(episodesUseCase: episodesUseCase)
    }
    
    
    var body: some View {
        List{
            ForEach(viewModel.state.episodeList, id: \.self.id){episode in
                Text(episode.name).onAppear(perform: {
                    if episode == viewModel.state.episodeList.last && viewModel.shouldLoadMoreItems(index: viewModel.state.episodeList.count - 1){
                        //viewModel.onTriggerEvent(stateEvent: EpisodeListEvents.NextPage())
                    }
                })
            }
        }
    
    }
}

 
