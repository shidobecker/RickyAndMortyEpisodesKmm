import SwiftUI
import shared

@main
struct iOSApp: App {
    
   private let iOSEpisodesComponent : IosEpisodesComponent = IosEpisodesComponent()
    
    init() {
        IOSEpisodeModulesKt.doInitIosEpisodeDependencies()
    }
     
	var body: some Scene {
        
		WindowGroup {
            let episodesUseCase = iOSEpisodesComponent.provideEpisodesUseCase()
            EpisodeListScreen(episodesUseCase: episodesUseCase)
            
            let a = iOSEpisodesComponent.provideEpisodeDetailUseCase()
            
		}
	}
}
