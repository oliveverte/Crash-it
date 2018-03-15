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
    private let MOVING_SPEED:Float = 0.3
    
    /** determine the factor of the speed of this item (sprite) */
    internal var speed_factor:Float
    /** determine the direction that this sprite are heading towards */
    internal var direction:CGVector
    
    
    init(texture: SKTexture?, color: UIColor, size: CGSize,
         speedFactor:Float = 1.0,
         direction: CGVector = CGVector(dx: 0, dy: -1)) {
        
        self.speed_factor = speedFactor
        self.direction = direction
        super.init(texture: texture, color: color, size: size)
    }
    
    
    required init?(coder aDecoder: NSCoder) {
        self.speed_factor = 1.0
        self.direction = CGVector(dx: 0, dy: -1)
        
        super.init(coder: aDecoder)
    }
    
    /**
     Allow the object to move following some rules.
     Define the comportment of the item.
     
     - Important:
     Call this function within the frames update.
    */
    func move() {
        let realSpeed = CGFloat(self.MOVING_SPEED * self.speed_factor)
        let realDirection = CGVector(dx: direction.dx * realSpeed,
                                     dy: direction.dy * realSpeed)
        
        self.position = CGPoint(x: self.position.x + realDirection.dx,
                                y: self.position.y + realDirection.dy)
    }
}























