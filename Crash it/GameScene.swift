//
//  GameScene.swift
//  Crash it
//
//  Created by Olivier Picard on 11/03/2018.
//  Copyright © 2018 Olivier Picard. All rights reserved.
//

import SpriteKit
import GameplayKit

class GameScene: SKScene {
    var player: ShuttlePlayer!
    var score = SKLabelNode()
    var stars:Stars!
    var shuttle_enemy_generator: ShuttleEnemyGenerator!
    
    
    override func didMove(to view: SKView) {
        Tools.scene_size = self.size
        
        player = ShuttlePlayer()
        player!.position = Tools.fromSceneToWorldPosition(screenSpacePos: CGPoint(x: 0.5, y: 0.2))
        self.addChild(player!)
        
        shuttle_enemy_generator = ShuttleEnemyGenerator(scene: self, target: player)
        
        score = self.childNode(withName: "Score") as! SKLabelNode
        score.position = Tools.fromSceneToWorldPosition(screenSpacePos: CGPoint(x: 0.5, y: 0.8))
        
        stars = Stars(scene: self, screenSize: self.size)
        
    }
    
    
    
    func touchDown(atPoint pos : CGPoint) {
        player.shoot()
    }
    
    func touchMoved(toPoint pos : CGPoint) {
        
    }
    
    func touchUp(atPoint pos : CGPoint) {
        
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        for t in touches { self.touchDown(atPoint: t.location(in: self)) }
    }
    
    
    override func touchesEnded(_ touches: Set<UITouch>, with event: UIEvent?) {
        for t in touches { self.touchUp(atPoint: t.location(in: self)) }
    }
    
    override func touchesCancelled(_ touches: Set<UITouch>, with event: UIEvent?) {
        for t in touches { self.touchUp(atPoint: t.location(in: self)) }
    }
    
    
    override func update(_ currentTime: TimeInterval) {
        stars.generate()
        shuttle_enemy_generator.generate(currentTime)
        
        var itemsToDelete:[SKNode] = []
        for item in self.children {
            if let movingItem = item as? MovingItem {
                movingItem.update(currentTime)
                if(movingItem.isOutOfScreen(screenSize: self.size)) {
                    itemsToDelete.append(item)
                }
            }
        }
        
        self.removeChildren(in: itemsToDelete)
    }
}


















