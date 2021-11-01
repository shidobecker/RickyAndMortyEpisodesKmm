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
        
        ZStack{
            VStack{
                Image("ricky")
                    .renderingMode(.original)
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                    .scaledToFit()
                    .padding(.top, 10).padding(.leading, 10.0).padding(.trailing, 10)
                
                Text("Episode Guide").frame(maxWidth: .infinity, alignment: .trailing)
                
                EpisodeList(episodeList: viewModel.state.episodeList, onNextPage: {
                    viewModel.onTriggerEvent(stateEvent: EpisodeListEvents.NextPage())
                })
                
                if viewModel.state.episodeList.count > 0 && viewModel.state.isLoading{
                    ProgressView("Searching more episodes..")

                }
                
            }.frame(maxWidth: .infinity, maxHeight: .infinity)
            
            if viewModel.state.isLoading {
                ProgressView("Searching Episodes..")
            }
        }
     
    }
  
}
 
