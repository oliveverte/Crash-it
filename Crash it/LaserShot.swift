//
//  LaserShot.swift
//  Crash it
//
//  Created by Olivier Picard on 13/03/2018.
//  Copyright © 2018 Olivier Picard. All rights reserved.
//

import Foundation
import SpriteKit

class LaserShot: MovingItem, Collisionable {
    let shooter: Shuttle
    var enable_collision: Bool
    
    private init(shooter: Shuttle, texture: SKTexture?, color: UIColor,
                 size: CGSize,
                 speedFactor: Float,
                 direction: CGVector) {
        self.shooter = shooter
        self.enable_collision = true
        super.init(texture: texture, color: color, size: size,
                   speedFactor: speedFactor, direction: direction)
        self.alpha = 0.6
    }

    required init?(coder aDecoder: NSCoder) {
        self.shooter = Shuttle(image: #imageLiteral(resourceName: "shuttle_1"), color: UIColor.white, stats: Shuttle.Stats())
        self.enable_collision = false
        super.init(coder: aDecoder)
    }
    
    convenience init(shooter: Shuttle, color: UIColor, direction: CGVector, rotation: CGFloat) {
        self.init(shooter: shooter,
                  texture: nil,
                  color: color,
                  size: CGSize(width: 3, height: 20),
                  speedFactor: 8.0,
                  direction: direction)
        self.zRotation = rotation
    }
    
    func inCollisionWith(item: Collisionable) {
//        if(item as? Shuttle != shooter) {
//            self.scene?.removeChildren(in: [self])
//        }
    }
    
}
