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
    var life = 100
    var list_of_laser_shoot: [LaserShot]

    private override init(texture: SKTexture?, color: UIColor,
                          size: CGSize, speedFactor: Float, direction: CGVector) {
        
        self.list_of_laser_shoot = []
        super.init(texture: texture,
                   color: color,
                   size: size,
                   speedFactor: speedFactor,
                   direction: direction)
    }
    
    convenience init(image: UIImage, size: CGSize, color: UIColor) {
        self.init(texture: SKTexture.init(image: image),
                  color: UIColor.red,
                  size: size,
                  speedFactor: 1.0,
                  direction: CGVector(dx: 0, dy: 1))
    }
    
    required init?(coder aDecoder: NSCoder) {
        self.list_of_laser_shoot = []
        super.init(coder: aDecoder)
    }

    
    func shoot() {
        let laser = LaserShot(color: self.color, direction: self.direction)
        laser.position = self.position
        self.list_of_laser_shoot.append(laser)
        self.scene!.addChild(laser)
    }
    
    override func update() {
        
    }
    
    
}
