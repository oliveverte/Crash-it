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
    
    
    init(image: UIImage, color: UIColor, stats: Shuttle.Stats, target: ShuttlePlayer) {
        self.target = target
        super.init(image: image, color: color, stats: stats)
        super.speed_factor = 2.0
    }
    
    required init?(coder aDecoder: NSCoder) {
        self.target = ShuttlePlayer()
        super.init(coder: aDecoder)
    }
    
    override func update(_ currentTime: TimeInterval) {
        super.update(currentTime)
        if self.stats.shoot_stats.canShoot(currentTime) { super.shoot() }
    }
    
    
    
}
