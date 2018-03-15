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
    /** determine the speed of this item (sprite) */
    internal var movingSpeed:Float
    /** determine the direction that this sprite are heading towards */
    internal var direction:CGVector
    
    init(texture: SKTexture?, color: UIColor, size: CGSize,
         _ movingSpeed:Float = 1.0,
         _ direction: CGVector = CGVector(dx: 0, dy: -1)) {
        
        self.movingSpeed = movingSpeed
        self.direction = direction
        super.init(texture: texture, color: color, size: size)
    }
    
    
    required init?(coder aDecoder: NSCoder) {
        self.movingSpeed = 1.0
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
        
    }
}
