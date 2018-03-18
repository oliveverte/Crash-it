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
    let STAR_SIZE = CGSize(width: 2, height: 2)
    let PERCENTAGE_OF_STARS = 6
    let screen_size: CGSize
    var current_scene: SKScene
    
    init(scene: SKScene, screenSize: CGSize) {
        self.screen_size = screenSize
        self.current_scene = scene
        
        // Crée aléatoirement des étoiles sur l'écran
        // Pour ne pas commencer le jeu avec un écran vide
        for _ in 0...PERCENTAGE_OF_STARS*10 {
            let randHeight = arc4random_uniform(UInt32(self.screen_size.height))
            let randWidth = arc4random_uniform(UInt32(self.screen_size.width))
            let star = MovingItem(texture: nil, size: self.STAR_SIZE)
            star.position = CGPoint(x: Int(randWidth), y: Int(randHeight))
            star.color = star.color.withAlphaComponent(CGFloat(
                arc4random_uniform(80 - 30) + 30) / 100)
            self.current_scene.addChild(star)
        }
    }
    
    
    /**
     Génère une étoile au sommet de l'écran (en dehors de la zone visible)
     et aléatoirement sur l'axe X
     La pourcentage d'apparation est défini
     à l'initialisation par 'percentageOfStars'
    */
    func generate() {
        // Si le nombre aléatoire n'est pas compris dans [0, percentage_of_stars]
        // alors on arrête, ne gérère pas l'étoile (pour le prochain
        // update si la chance est de notre côté)
        if(arc4random_uniform(101) > PERCENTAGE_OF_STARS*Int(MovingItem.base_moving_speed)) { return }
        
        // hauteur de l'écran + 10 pour être sûre que
        // l'étoile sera générer en dehors de l'écran
        let yPos = screen_size.height + 10
        let xPos = CGFloat(arc4random_uniform(UInt32(screen_size.width)))
        
        let star = MovingItem(texture: nil, size: STAR_SIZE)
        star.position = CGPoint(x: xPos, y: yPos)
        star.color = UIColor.white
        star.alpha = CGFloat(arc4random_uniform(80 - 30) + 30) / 100
        
        self.current_scene.addChild(star)
        
    }
    
    
    
    
    
}


























