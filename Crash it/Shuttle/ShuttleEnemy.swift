//
//  Enemy.swift
//  Crash it
//
//  Created by Olivier Picard on 13/03/2018.
//  Copyright © 2018 Olivier Picard. All rights reserved.
//

import Foundation
import SpriteKit

class ShuttleEnemy: Shuttle {
    let target: ShuttlePlayer
    var deltaTime_check_targetPosition: TimeInterval
    private var previous_time_updatedDirection: TimeInterval
    
    
    init(image: UIImage, color: UIColor, stats: Shuttle.Stats, target: ShuttlePlayer) {
        self.target = target
        self.deltaTime_check_targetPosition = 2
        self.previous_time_updatedDirection = 0
        super.init(image: image, color: color, stats: stats)
        super.speed_factor = 2.0
        super.lifeBar.position = CGPoint(x: self.position.x,
                                         y: self.position.y + self.size.height/2 + super.lifeBar.size.height/2 + 8)
        
    }
    
    required init?(coder aDecoder: NSCoder) {
        self.target = ShuttlePlayer(coder: aDecoder)!
        self.previous_time_updatedDirection = 0
        self.deltaTime_check_targetPosition = 2
        super.init(coder: aDecoder)
    }
    
    override func update(_ currentTime: TimeInterval) {
        super.update(currentTime)
        if self.stats.shoot_stats.canShoot(currentTime) {
            shoot(direction: self.direction, rotation: self.zRotation)
        }
        if(currentTime - self.previous_time_updatedDirection >= self.deltaTime_check_targetPosition
            && self.position.y - target.position.y > self.target.position.y + self.scene!.size.height/15) {
            
            self.previous_time_updatedDirection = currentTime
            updateDirectionToTarget()
        }
    }
    
    func updateDirectionToTarget() {
        // Soit un triangle rectangle en A, B est self et C la target
        let len_BA = self.position.y - target.position.y
        let len_CA = target.position.x - self.position.x
        
        let angleRadian = atan(len_CA/len_BA)
        self.zRotation = angleRadian
        self.direction = CGVector(dx: sin(angleRadian), dy: -cos(angleRadian))
        
    }

    override func inCollisionWith(item: Collisionable) {
        var increaseScore = 0
        if let laser = item as? LaserShot {
            if type(of: laser.shooter) == ShuttleEnemy.self { return }
            self.lifeBar.value -= laser.shooter.stats.attack
            if self.lifeBar.value == 0 { increaseScore += 1 }
        } else if let _ = item as? ShuttlePlayer {
            self.lifeBar.value = 0
            increaseScore += 1
        }
        
        if(increaseScore > 0) {
            let gameScene = self.scene! as! GameScene
            gameScene.increaseScore(1)
            gameScene.removeChildren(in: [self])
        }
    }
    
}







