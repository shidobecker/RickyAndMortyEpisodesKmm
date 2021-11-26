//
//  EpisodeDetailViewModel.swift
//  iosApp
//
//  Created by Julio Ribeiro on 24/11/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

class EpisodeDetailViewModel: ObservableObject{
    
    //dependencies
    private let episodeId: String
    private let episodeDetailUseCase: EpisodeDetailUseCase
    
    @Published var state: EpisodeState = EpisodeState()
    
    init(episodeId: String, episodeUseCase: EpisodeDetailUseCase){
        self.episodeId = episodeId
        self.episodeDetailUseCase = episodeUseCase
        
        onTriggerEvent(event: EpisodeDetailEvent.LoadEpisode())
    }
    
    
    func onTriggerEvent(event: EpisodeDetailEvent){
        switch event {
        case is EpisodeDetailEvent.LoadEpisode:
            fetchEpisode()
        default:
            doNothing()
        }
    }
    
    
    private func doNothing(){}
    
    private func fetchEpisode(){
        do {
            try episodeDetailUseCase.fetchEpisode(id: episodeId).collectCommon(coroutineScope: nil, callBack: { dataState in
                if dataState != nil{
                    
                    let data = dataState?.data
                    let loading =  dataState?.isLoading
                    
                    self.updateState(isLoading: loading)
                    
                    if data != nil{
                        self.setEpisode(episode: data!)
                    }
                    
                    //Handle error from CommonDataState
                    let errorState  = dataState?.error
                    let isDefaultException = errorState?.isDefaultApplicationException()
                    let errorCode = errorState?.code
                    let errorMessage = errorState?.message
                    
                }
            })
            
            
        } catch  {
            print("Error Fetching Episode")
        }
        
    }
    
    
    
    func setEpisode(episode: Episode){
        let currentState = self.state.copy() as! EpisodeState
        self.state = self.state.doCopy(
            isIdle : currentState.isIdle,
            isLoading : currentState.isLoading,
            error : currentState.error,
            episode: episode
        )
    }
    
    //Update the state property with it's copy
    private func updateState(isIdle: Bool? = nil, isLoading: Bool? = nil){
        let currentState = (self.state.copy() as! EpisodeState)
        self.state = self.state.doCopy(
            isIdle: currentState.isIdle,
            isLoading: isLoading ?? currentState.isLoading,
            error : currentState.error,
            episode : currentState.episode)
        
    }
    
    
}
