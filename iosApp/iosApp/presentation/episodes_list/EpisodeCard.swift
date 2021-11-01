//
//  EpisodeCard.swift
//  iosApp
//
//  Created by Julio Ribeiro on 01/11/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI


struct EpisodeCard: View{
    
    private let episode: Episode
    
    init(episode: Episode){
        self.episode = episode
    }
    
    var body: some View{
             VStack {
                Text(episode.name).font(.title2).foregroundColor(Color.white).bold() .padding(.top, 4)
                
                Text(episode.episode).font(.body).foregroundColor(Color.white)
                    .padding(.top, 4)
                    .alignmentGuide(.leading) { d in d[.leading] }
                
             Text("Air date: \(episode.airDate)").font(.body) .padding(.top, 4).foregroundColor(Color.white)
                
             }.frame(maxWidth: .infinity).background(Color.black).cornerRadius(10.0).padding(.top, 8).padding(.bottom, 8)
        
    }
    
    
    
}
