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
    var player: ShuttlePlayer?
    var score = SKLabelNode()
    var stars:Stars?
    
    override func didMove(to view: SKView) {
        player = ShuttlePlayer(image: #imageLiteral(resourceName: "shuttle_1"),
                               size: Tools.fromSceneToWorldSize(sceneSpaceSize: CGSize(width: 0.1, height: 0.05),
                                                                sceneSize: self.size))

        player!.position = Tools.fromSceneToWorldPosition(screenSpacePos: CGPoint(x: 0.5, y: 0.2),
                                                          sceneSize: self.size)
        self.addChild(player!)
        
        score = self.childNode(withName: "Score") as! SKLabelNode
        score.position = Tools.fromSceneToWorldPosition(screenSpacePos: CGPoint(x: 0.5, y: 0.8),
                                                        sceneSize: self.size)
        
        stars = Stars(scene: self, screenSize: self.size)
        
    }
    
    
    
    func touchDown(atPoint pos : CGPoint) {
        player?.shoot()
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
        // Called before each frame is rendered
        stars!.generate()
        var itemsToDelete:[SKNode] = []
        for item in self.children {
            if let movingItem = item as? MovingItem {
                movingItem.update()
                if(movingItem.isOutOfScreen(screenSize: self.size)) {
                    itemsToDelete.append(item)
                }
            }
        }
        
        self.removeChildren(in: itemsToDelete)
    }
}


















