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
    var starsGenerator_topLayer:StarsGenerator!
    var starsGenerator_bottomLayer:StarsGenerator!
    var shuttle_enemy_generator: ShuttleEnemyGenerator!
    
    
    override func didMove(to view: SKView) {
        Tools.scene_size = self.size
        
        player = ShuttlePlayer()
        player!.position = Tools.fromSceneToWorldPosition(screenSpacePos: CGPoint(x: 0.5, y: 0.2))
        self.addChild(player!)
        
        shuttle_enemy_generator = ShuttleEnemyGenerator(scene: self, target: player)
        
        score = self.childNode(withName: "Score") as! SKLabelNode
        score.position = Tools.fromSceneToWorldPosition(screenSpacePos: CGPoint(x: 0.5, y: 0.8))
        
        starsGenerator_topLayer = StarsGenerator(scene: self,
                                                    screenSize: self.size,
                                                    starsPercent: 8,
                                                    opacityRange: Tools.Interval(min: 40, max: 70),
                                                    starSize: CGSize(width: 1, height: 2),
                                                    speedFactor: 1.0)
        
        starsGenerator_bottomLayer = StarsGenerator(scene: self,
                                                    screenSize: self.size,
                                                    starsPercent: 13,
                                                    opacityRange: Tools.Interval(min: 30, max: 50),
                                                    starSize: CGSize(width: 1, height: 1),
                                                    speedFactor: 0.4)
    }
    
    
    
    func touchDown(atPoint pos : CGPoint) {
        if(pos.x < self.size.width/3){
            player.direction.dx = -3
        } else if(pos.x > (self.size.width/3) * 2) {
            player.direction.dx = 3
        } else {
            player.shoot(direction: CGVector(dx: 0, dy: 1))
        }
    }
    
    func touchMoved(toPoint pos : CGPoint) {
        
    }
    
    func touchUp(atPoint pos : CGPoint) {
        player.direction.dx = 0
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
        starsGenerator_topLayer.generate()
        starsGenerator_bottomLayer.generate()
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


















