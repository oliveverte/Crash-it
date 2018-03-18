//
//  Player.swift
//  Crash it
//
//  Created by Olivier Picard on 13/03/2018.
//  Copyright © 2018 Olivier Picard. All rights reserved.
//

import Foundation
import SpriteKit

class Shuttle : MovingItem {
    struct ShuttleStat {
        var life = 100
    }

//    private override init(texture: SKTexture?, color: UIColor,
//                          size: CGSize, speedFactor: Float, direction: CGVector) {
//        super.init(texture: texture,
//                   color: color,
//                   size: size,
//                   speedFactor: speedFactor,
//                   direction: direction)
//    }
    
    init(image: UIImage, size: CGSize, color: UIColor) {
        super.init(texture: SKTexture.init(image: image),
                  color: UIColor.red,
                  size: size,
                  speedFactor: 1.0,
                  direction: CGVector(dx: 0, dy: 1))
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }

    
    func shoot() {
        let laser = LaserShot(color: self.color, direction: self.direction)
        laser.position = self.position
        self.scene!.addChild(laser)
    }
    
    override func update() {}
    
    
}
