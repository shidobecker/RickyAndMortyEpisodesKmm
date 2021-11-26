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
        
        UINavigationBar.appearance().setBackgroundImage(UIImage(), for: .default)
        UINavigationBar.appearance().shadowImage = UIImage()
    }
    
    
    
    @State var isNavigationBarHidden: Bool = true

    var body: some View {
       NavigationView{
            ZStack{
                
                VStack{
                    
                    Image("ricky")
                        .renderingMode(.original)
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                        .scaledToFit()
                        .padding(.top, 10).padding(.leading, 10.0).padding(.trailing, 10)
                    
                    Text("Episode Guide")
                        .bold().foregroundColor(.white)
                        .frame(maxWidth: .infinity, alignment: .trailing).padding(.trailing, 16)
                    
       
                    EpisodeList(episodeList: viewModel.state.episodeList, onNextPage: {
                        viewModel.onTriggerEvent(stateEvent: EpisodeListEvent.NextPage())
                    })
                    
                    if viewModel.state.episodeList.count > 0 && viewModel.state.isLoading{
                        ProgressView("Searching more episodes..").foregroundColor(.white).progressViewStyle(CircularProgressViewStyle(tint: Color.white))
                    }
                    
                }.frame(maxWidth: .infinity, maxHeight: .infinity)
                
                if viewModel.state.isLoading {
                    ProgressView("Searching Episodes..")
                }
                
                
            }.background(Color.black)
            
       }
       .navigationBarTitle("Hidden Title", displayMode: .inline).navigationBarHidden(self.isNavigationBarHidden)
     
    }
  
}
 
