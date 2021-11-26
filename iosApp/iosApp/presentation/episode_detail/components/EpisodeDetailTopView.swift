//
//  EpisodeDetailTopView.swift
//  iosApp
//
//  Created by Julio Ribeiro on 25/11/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared


struct EpisodeDetailTopView: View {
    
    private let episode: Episode
    
    init(episode: Episode){
        self.episode = episode
    }
    
    
    var body: some View {
        let lightGray: Color  = Color(red: 206 / 255, green: 208 / 255, blue: 208 / 255)
        let darkGray: Color = Color(red: 91 / 255, green: 92 / 255, blue: 92 / 255)
        
        VStack {
            
            VStack{
                Image(self.episode.imageName).resizable().frame(maxWidth: .infinity, maxHeight: 210).cornerRadius(0.0)
                
                HStack{
                    Text("Summary").font(.title2).foregroundColor(Color.white).bold().frame(maxWidth: .infinity)
                    
                }.padding(.bottom, 8).frame(width: .infinity, height: 60, alignment: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/).background(darkGray).padding(.top, -8)
                
                
                Text(self.episode.name).font(.caption).foregroundColor(Color.black)
                
                Text(self.episode.episode).font(.caption).foregroundColor(Color.black).alignmentGuide(.leading) { d in d[.leading] }
                
                Text("Air date: \(self.episode.airDate)").font(.caption).padding(.bottom, 16).foregroundColor(Color.black)
                
                
                HStack{
                    Text("Character List:").font(.title2).foregroundColor(Color.white).bold().frame(maxWidth: .infinity)
                    
                }.padding(.bottom, 8).frame(width: .infinity, height: 60, alignment: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/).background(lightGray).padding(.top, -8)
            }
            
        }.frame(maxWidth: .infinity).background(Color.white).padding(.bottom, 16)
    }
}


