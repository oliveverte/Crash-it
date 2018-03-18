//
//  MovingItem.swift
//  Crash it
//
//  Created by Olivier Picard on 13/03/2018.
//  Copyright © 2018 Olivier Picard. All rights reserved.
//

import Foundation
import SpriteKit

class MovingItem: SKSpriteNode {
    static internal var base_moving_speed:Float = 1
    
    /** détermine les marges en dehors de l'écran à partir
     desquels on peut suprimer un élément
     
    - Note:
     Des items(ex : étoiles) peuvent être générer hors de l'écran proche du bord.
     Avant de dire qu'un item est hors de l'écran on vérfie qu'il soit très éloignés du bord de l'écran
     pour éviter la confusion avec les item récent et ceux en fin de vie d'où l'utiliter de définir
     cette variable pour avoir une marge fixe et connu
     */
    private let MARGIN_OUT_OF_SCREEN_TO_DELETE:CGFloat = 30
    
    /** determine the factor of the speed of this item (sprite) */
    internal var speed_factor: Float = 1
    /** determine the direction that this sprite are heading towards */
    internal var direction: CGVector
    
 
    
    init( texture: SKTexture?, color: UIColor, size: CGSize, speedFactor:Float = 2.5,
          direction: CGVector = CGVector(dx: 0, dy: -1)) {
        
        self.speed_factor = speedFactor
        self.direction = direction
        super.init(texture: texture, color: color, size: size)
    }
    
    
    convenience init(texture: SKTexture?, size: CGSize) {
        self.init(texture: texture, color: UIColor.white, size: size)
    }
    
    
    required init?(coder aDecoder: NSCoder) {
        self.speed_factor = 1
        self.direction = CGVector(dx: 0, dy: -1)
        super.init(coder: aDecoder)
    }
    
    /**
     Autorise l'item à se déplacer suivant la vitesse et la direction.
     - Important:
     il parait judicieux d'appeler cette fonction à chaque update de la scène
    */
    func update(_ currentTime: TimeInterval) {
        let realSpeed = CGFloat(MovingItem.base_moving_speed * self.speed_factor)
        let realDirection = CGVector(dx: direction.dx * realSpeed,
                                     dy: direction.dy * realSpeed)
        
        self.position = CGPoint(x: self.position.x + realDirection.dx,
                                y: self.position.y + realDirection.dy)
    }
    
    /**
     Détermine si l'item est hors de l'écran (n'est plus visible)
     - parameters:
        - screenSize : représente la taille de l'écran
     - returns:
     Retourne "true" si l'item est hors de l'écran sinon retourne "false"
     */
    func isOutOfScreen(screenSize: CGSize) -> Bool{
        // Anchor point 0.5 par défaut donc on divise la taille par 2
        if(self.position.y + self.size.height/2 < -MARGIN_OUT_OF_SCREEN_TO_DELETE
            || self.position.y - self.size.height/2 > screenSize.height + MARGIN_OUT_OF_SCREEN_TO_DELETE
            || self.position.x - self.size.width/2 > screenSize.width + MARGIN_OUT_OF_SCREEN_TO_DELETE
            || self.position.x + self.size.width/2 < -MARGIN_OUT_OF_SCREEN_TO_DELETE) {
            
            return true
        }
        
        return false
    }
    
    
    
    
}























