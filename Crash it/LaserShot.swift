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
    }

    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
    
    convenience init(color: UIColor, direction: CGVector) {
        self.init(texture: nil,
                  color: color,
                  size: CGSize(width: 10, height: 80),
                  speedFactor: 2.0,
                  direction: direction)
    }
    
}
