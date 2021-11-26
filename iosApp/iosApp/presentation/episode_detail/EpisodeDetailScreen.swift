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
        
        UINavigationBar.appearance().setBackgroundImage(UIImage(), for: .default)
        UINavigationBar.appearance().shadowImage = UIImage()
    }
    
    
    
    var body: some View {
        
        if viewModel.state.isLoading {
            ProgressView("Searching episode data...").foregroundColor(.white).progressViewStyle(CircularProgressViewStyle(tint: Color.white))
        }
        
        if viewModel.state.episode != nil {
            let episode = viewModel.state.episode!
                        
            ScrollView {
                
                EpisodeDetailTopView(episode: episode)
     
                let columns = [ GridItem(.adaptive(minimum: 100))  ]
                
                let characters = episode.characters
  
                LazyVGrid(columns: columns, spacing: 20) {
                    ForEach(characters, id: \.self) { character in
                        CharacterCard(character: character)
                    }
                }
                .padding(16)
            }.frame(maxHeight: .infinity)
        }
        
    }
}


