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
    }
    
    required init?(coder aDecoder: NSCoder) {
        self.target = ShuttlePlayer()
        self.previous_time_updatedDirection = 0
        self.deltaTime_check_targetPosition = 2
        super.init(coder: aDecoder)
    }
    
    override func update(_ currentTime: TimeInterval) {
        super.update(currentTime)
        if self.stats.shoot_stats.canShoot(currentTime) { super.shoot(direction: CGVector(dx: 0, dy: -1)) }
        updateDirectionToTarget()
        if(currentTime - self.previous_time_updatedDirection >= self.deltaTime_check_targetPosition) {
            self.previous_time_updatedDirection = currentTime
            
        }
    }
    
    func updateDirectionToTarget() {
        // Soit un triangle rectangle en A, B est self et C la target
        let len_BA = self.position.y - target.position.y
        let len_CA = target.position.x - self.position.x
        
        let angleRadian = atan(len_CA/len_BA)
        let angleDegree = angleRadian * 180 / .pi
        self.zRotation = angleRadian
        self.direction = CGVector(dx: cos(angleDegree), dy: sin(angleDegree))
    }

    
}







