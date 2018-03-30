//
//  ShuttlePlayer.swift
//  Crash it
//
//  Created by Olivier Picard on 18/03/2018.
//  Copyright © 2018 Olivier Picard. All rights reserved.
//

import Foundation
import SpriteKit

class ShuttlePlayer: Shuttle {
    
    init() {
        super.init(image: #imageLiteral(resourceName: "shuttle_1"),
                   color: UIColor.red,
                   stats: Shuttle.Stats(defense: 200, attack: 30,
                                         shootStats: Shuttle.Stats.ShootStats(deltaTimeToShoot: 1/4,
                                                                              probSuccessFire: 40)))
        super.direction = CGVector(dx: 0, dy: 0)
        super.lifeBar.position = CGPoint(x: self.position.x,
                                         y: self.position.y - self.size.height/2 - super.lifeBar.size.height/2 - 8)
        
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
    
    
    override func update(_ currentTime: TimeInterval) {
        if(self.direction.dx < 0 && self.position.x > self.size.width/2
            || self.direction.dx > 0 && self.position.x < (self.scene?.size.width)! - self.size.width/2) {
            super.update(currentTime)
        }
        
        if self.stats.shoot_stats.canShoot(currentTime) {
            shoot(direction: CGVector(dx: 0, dy: 1), rotation: self.zRotation)
        }
    }
    
    override func inCollisionWith(item: Collisionable) {
        if let laser = item as? LaserShot {
            if(laser.shooter == self) { return }
            self.lifeBar.value -= laser.shooter.stats.attack
        } else if item is ShuttleEnemy {
            self.lifeBar.value = 0
        } else if let asteroid = item as? Asteroid {
            self.lifeBar.value -= asteroid.dammage
        }
        
        if self.lifeBar.value == 0 {
            if let gameScene = self.scene as? GameScene { gameScene.gameOver() }
            self.scene?.addChild(Explosion(position: self.position))
            self.scene?.removeChildren(in: [self])
            
        }
    }
    
    
}
