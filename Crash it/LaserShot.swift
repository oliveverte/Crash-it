//
//  LaserShot.swift
//  Crash it
//
//  Created by Olivier Picard on 13/03/2018.
//  Copyright © 2018 Olivier Picard. All rights reserved.
//

import Foundation
import SpriteKit

class LaserShot: MovingItem {
    
    private override init(texture: SKTexture?, color: UIColor, size: CGSize, speedFactor: Float, direction: CGVector) {
        super.init(texture: texture, color: color, size: size,
                   speedFactor: speedFactor, direction: direction)
        self.alpha = 0.6
    }

    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
    
    convenience init(color: UIColor, direction: CGVector, rotation: CGFloat) {
        self.init(texture: nil,
                  color: color,
                  size: CGSize(width: 3, height: 20),
                  speedFactor: 8.0,
                  direction: direction)
        self.zRotation = rotation
    }
    
}
