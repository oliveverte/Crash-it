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
    
    var scrollingSpeed:CGFloat = 0.5
    var player = Player()
    var score = SKLabelNode()
    var stars:Stars?
    
    override func didMove(to view: SKView) {
        player = Player(texture: SKTexture(image: #imageLiteral(resourceName: "shuttle_1")), size: CGSize(width: 86, height: 55))
        player.position = self.convertToSceneSpace(CGPoint.init(x: 0.5, y: 0.2))
        
        score = self.childNode(withName: "Score") as! SKLabelNode
        score.position = self.convertToSceneSpace(CGPoint.init(x: 0.5, y: 0.8))
        
        stars = Stars(scene: self, screenSize: self.size, percentageOfStars: 30)
       
    }
    
    /**
     Convert a percentage of the current scene to a real point on screen (physic pixel coordinate).
     
     - returns:
     CGPoint represent coordinate of a point on screen (pixel).

     - parameters:
        - pos: CGpoint: between [0,1] on the scene (percentage of the scene).
     */
    func convertToSceneSpace(_ pos: CGPoint) -> CGPoint {
        return CGPoint.init(x: self.size.width * pos.x,
                            y: self.size.height * pos.y)
    }
    
    
    
    func touchDown(atPoint pos : CGPoint) {
        // When finger is down
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
        stars?.move()
    }
}


















