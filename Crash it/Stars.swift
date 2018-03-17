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
    
//    var list_of_stars: [MovingItem]
    let screen_size: CGSize
    var current_scene: SKScene
    
    init(scene: SKScene, screenSize: CGSize) {
        self.screen_size = screenSize
//        self.list_of_stars = []
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
//            self.list_of_stars.append(star)
        }
    }
    
    
//    func update() {
//        generate()
//        var index_of_stars_to_delete: [Int] = []
//        var items_to_remove_from_scene: [SKNode] = []
//        var deleted_item_counter = 0
//        var index_counter = 0
//
//        for star in list_of_stars {
//            star.update()
//            if(star.isOutOfScreen(screenSize: self.current_scene.size)) {
//                index_of_stars_to_delete.append(index_counter)
//                items_to_remove_from_scene.append(star)
//            }
//            index_counter += 1
//        }
//        current_scene.removeChildren(in: items_to_remove_from_scene)
//
//        for i in 0..<items_to_remove_from_scene.count {
//            let realIndex = index_of_stars_to_delete[i] - deleted_item_counter
//            self.list_of_stars.remove(at: realIndex)
//            deleted_item_counter += 1
//        }
//    }
    
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
        let yPos = screen_size.height
        let xPos = CGFloat(arc4random_uniform(UInt32(screen_size.width)))
        
        let star = MovingItem(texture: nil, size: STAR_SIZE)
        star.position = CGPoint(x: xPos, y: yPos)
        star.color = UIColor.white
//            UIColor.init(red: 1, green: 1, blue: 1,
//                                  alpha: CGFloat(arc4random_uniform(80 - 30) + 30) / 100)
        star.alpha = CGFloat(arc4random_uniform(80 - 30) + 30) / 100
        
//        self.list_of_stars.append(star)
        self.current_scene.addChild(star)
        
    }
    
    
    
    
    
}


























