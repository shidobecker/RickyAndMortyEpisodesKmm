//
//  CharacterCard.swift
//  iosApp
//
//  Created by Julio Ribeiro on 25/11/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared
import SDWebImageSwiftUI

struct CharacterCard: View {
    
    private let character: Character
    
    init(character: Character){
        self.character = character
    }
    
    var body: some View {
 
        VStack {
            WebImage(url: URL(string: character.image)).resizable().placeholder(Image(systemName: "photo"))
                .placeholder{
                    RoundedRectangle(cornerRadius: 6).foregroundColor(.white)
                }.indicator(.activity)
                .transition(.fade(duration: 0.5))
                .scaledToFill().frame(width: 100, height: 100, alignment: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/).clipped()
            
          
            Text(character.name).font(.body).foregroundColor(Color.black) 
                .alignmentGuide(.leading) { d in d[.leading] }
            
            Text(character.species).font(.caption).foregroundColor(Color.black)
                .alignmentGuide(.leading) { d in d[.leading] }
            
            Text(character.status).font(.caption).foregroundColor(Color.black)
                .alignmentGuide(.leading) { d in d[.leading] }
                        
        }.frame(maxWidth: 200, maxHeight: 200, alignment: .center).cornerRadius(10.0).padding(.bottom, 16)
    }
}
 
