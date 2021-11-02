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
        let lightGray: Color  = Color(red: 206 / 255, green: 208 / 255, blue: 208 / 255)
        let darkGray: Color = Color(red: 91 / 255, green: 92 / 255, blue: 92 / 255)
        
        VStack {
            Image(episode.imageName).resizable().frame(maxWidth: .infinity, maxHeight: 210).cornerRadius(10.0)
            
            HStack{
                Text(episode.name).font(.title2).foregroundColor(Color.white).bold().frame(maxWidth: .infinity)
            }.padding(.bottom, 8).frame(width: .infinity, height: 50, alignment: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/).background(darkGray).padding(.top, -8)
            
            Text(episode.episode).font(.body).foregroundColor(Color.black)
                .padding(.top, 8)
                .alignmentGuide(.leading) { d in d[.leading] }
            
            Text("Air date: \(episode.airDate)").font(.body) .padding(.top, 4).padding(.bottom, 16).foregroundColor(Color.black)
            
        }.frame(maxWidth: .infinity).background(lightGray).cornerRadius(10.0).padding(.bottom, 16)
        
    }
    
    
    
}
