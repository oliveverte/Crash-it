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
    struct Stats {
        let defense: Int
        let attack: Int
        let linked_bonus: [Bonus]
        
        init(defense: Int, attack: Int) {
            self.defense = defense
            self.attack = attack
            self.linked_bonus = []
        }
        
        init(state: Shuttle.Stats) {
            self.init(defense: state.defense, attack: state.attack)
        }
    }
    
    let stats: Shuttle.Stats
    
    
    init(image: UIImage, color: UIColor, stats: Shuttle.Stats) {
        self.stats = stats
        super.init(texture: SKTexture.init(image: image),
                  color: UIColor.red,
                  size: Tools.fromSceneToWorldSize(sceneSpaceSize: CGSize(width: 0.1, height: 0.05)),
                  speedFactor: 1.5,
                  direction: CGVector(dx: 0, dy: -1))
    }
    
    required init?(coder aDecoder: NSCoder) {
        self.stats = Shuttle.Stats(defense: 0, attack: 0)
        super.init(coder: aDecoder)
    }

    
    func shoot() {
        let laser = LaserShot(color: self.color, direction: self.direction)
        laser.position = self.position
        self.scene!.addChild(laser)
    }
    
    
    
}
