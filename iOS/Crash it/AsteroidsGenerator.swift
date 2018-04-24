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
    private let asteroid_size = CGSize(width: 50, height: 50)
    private var previousTime_generation: TimeInterval
    private var deltatTime_generation: TimeInterval = 0.4 // en seconde
    var asteroids_percent: Int = 15
    var enable:Bool = false
    
    init(scene: SKScene) {
        self.scene = scene
        self.previousTime_generation = 0
    }
    
    private func create(pos: CGPoint) {
        let asteroid = Asteroid(size: asteroid_size)
        asteroid.position = pos
        self.scene.addChild(asteroid)
    }
    
    func generate(_ currentTime: TimeInterval) {
        if(!self.enable) { return }
        if currentTime - self.previousTime_generation < self.deltatTime_generation { return }
        self.previousTime_generation = currentTime
        if arc4random_uniform(101) > self.asteroids_percent { return }
        
        let yPos = scene.size.height + self.asteroid_size.height/2 + 3
        let xPos = CGFloat(
            arc4random_uniform(UInt32(
                self.scene.size.width - self.asteroid_size.width))) + self.asteroid_size.width/2
        
        create(pos: CGPoint(x: xPos, y: yPos))
    }

    
    func restaure(item: Tools.ItemConf) {
//        create(pos: item.position!)
    }
    
    
}



















