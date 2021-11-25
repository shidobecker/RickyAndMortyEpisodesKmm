//
//  EpisodeDetailScreen.swift
//  iosApp
//
//  Created by Julio Ribeiro on 24/11/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct EpisodeDetailScreen: View {
    
    private let iOSEpisodesComponent : IosEpisodesComponent = IosEpisodesComponent()

    //dependencies
    private let episodeUseCase : EpisodeDetailUseCase
    private let episodeId: String
    
    @ObservedObject var viewModel: EpisodeDetailViewModel
    
    init(episodeId: String) {
        self.episodeId = episodeId
        self.episodeUseCase = iOSEpisodesComponent.provideEpisodeDetailUseCase()
        viewModel = EpisodeDetailViewModel(episodeId: self.episodeId, episodeUseCase: self.episodeUseCase)
    }
    
    
    
    var body: some View {
        if viewModel.state.episode != nil {
            let name = viewModel.state.episode?.name
            
            Text("Hi, id is \(name!)")
        }
       
    }
}

 
