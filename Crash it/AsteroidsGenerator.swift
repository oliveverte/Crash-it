//
//  AsteroidsGenerator.swift
//  Crash it
//
//  Created by Olivier Picard on 23/03/2018.
//  Copyright © 2018 Olivier Picard. All rights reserved.
//

import Foundation
import SpriteKit

class AsteroidsGenerator {
    private let scene: SKScene
    private var previousTime_generation: TimeInterval
    private var deltatTime_generation: TimeInterval = 0.4 // en seconde
    var asteroids_percent: Int = 15
    
    init(scene: SKScene) {
        self.scene = scene
        self.previousTime_generation = 0
    }
    
    func generate(_ currentTime: TimeInterval) {
        if currentTime - self.previousTime_generation < self.deltatTime_generation { return }
        self.previousTime_generation = currentTime
        if arc4random_uniform(101) > self.asteroids_percent { return }
        
        let asteroid_size = CGSize(width: 50, height: 50)
        let yPos = scene.size.height + asteroid_size.height/2 + 3
        let xPos = CGFloat(
            arc4random_uniform(UInt32(
                self.scene.size.width - asteroid_size.width))) + asteroid_size.width/2
        
        let asteroid = Asteroid(size: asteroid_size)
        asteroid.position = CGPoint(x: xPos, y: yPos)
        self.scene.addChild(asteroid)
    }
}
