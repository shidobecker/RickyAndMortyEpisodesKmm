//
//  EpisodesListViewModel.swift
//  iosApp
//
//  Created by Julio Ribeiro on 28/10/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

class EpisodesListViewModel: ObservableObject{
    
    //dependencies
    private let episodesUseCase : EpisodeListUseCase
    
    @Published var state: EpisodeListState = EpisodeListState()
    
    init(episodesUseCase: EpisodeListUseCase){
        self.episodesUseCase = episodesUseCase
        onTriggerEvent(stateEvent: EpisodeListEvents.LoadEpisodes())
    }
    
    func updateState(isLoading: Bool? = nil, page: Int? = nil){
        let currentState = (self.state.copy() as! EpisodeListState)
        self.state = self.state.doCopy(
            isIdle: currentState.isIdle,
            isLoading: isLoading ?? currentState.isLoading,
            errorHappened: currentState.errorHappened,
            page : Int32(page ?? Int(currentState.page)),
            episodeList : currentState.episodeList)
                
    }
    
    func onTriggerEvent(stateEvent: EpisodeListEvents){
        switch stateEvent {
        case is EpisodeListEvents.LoadEpisodes:
           loadEpisodes()
        case is EpisodeListEvents.NextPage:
            doNothing()
        default:
            doNothing()
        }
     }
    
    func doNothing(){
        
    }
    
    func loadEpisodes(){
        let currentState = (self.state.copy() as! EpisodeListState)
        
        do{
            try episodesUseCase.fetchEpisodes(page: state.page).collectCommon(coroutineScope: nil, callBack: { dataState in
                if dataState != nil{
                    let data = dataState?.data
                    let loading =  false
                
                    self.updateState(isLoading: loading)
                    
                    if data != nil{
                        self.appendEpisodes(episodes: data as! [Episode])
                    }
                    
                    
                }
                
            })
        }catch{
            print("Error")
        }
        
    }
    
    func appendEpisodes(episodes: [Episode]){
        var currentState = self.state.copy() as! EpisodeListState
        var currentEpisodes = currentState.episodeList
        currentEpisodes.append(contentsOf: episodes)
        self.state = self.state.doCopy(
            isIdle : currentState.isIdle,
            isLoading : currentState.isLoading,
            errorHappened: false,
            page : currentState.page,
            episodeList : currentEpisodes
        )
        
    }
    
 
    
    
}
