//
//  Stars.swift
//  Crash it
//
//  Created by Olivier Picard on 15/03/2018.
//  Copyright © 2018 Olivier Picard. All rights reserved.
//

import Foundation
import SpriteKit

/**
 Gestion semi-automatisée de l'ensemble des étoiles sur l'écran
 */
class Stars {
    var list_of_stars: [MovingItem]
    let screen_size: CGSize
    let percentage_of_stars: Int
    
    init(scene: SKScene, screenSize: CGSize, percentageOfStars: Int) {
        self.screen_size = screenSize
        self.percentage_of_stars = percentageOfStars
        self.list_of_stars = []
        
        // Crée aléatoirement des étoiles sur l'écran
        // Pour ne pas commencer le jeu avec un écran vide
        for _ in 0...percentageOfStars {
            let randHeight = arc4random_uniform(UInt32(self.screen_size.height))
            let randWidth = arc4random_uniform(UInt32(self.screen_size.width))
            let star = MovingItem(texture: nil,
                                  color: UIColor.white,
                                  size: CGSize(width: 2, height: 2))
            star.position = CGPoint(x: Int(randWidth), y: Int(randHeight))
            scene.addChild(star)
            self.list_of_stars.append(star)
        }
    }
    
    
}
