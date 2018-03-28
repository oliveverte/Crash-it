//
//  GameOverScreen.swift
//  Crash it
//
//  Created by Olivier Picard on 25/03/2018.
//  Copyright © 2018 Olivier Picard. All rights reserved.
//

import Foundation
import SpriteKit

class GameOverScreen {
    let scene: GameScene
    let scoreText_label: SKLabelNode!
    var score_label: SKLabelNode!
    var save_button:Button
    var retry_button:Button
    var menu_button:Button
    
    
    init(scene: GameScene) {
        self.scene = scene
        self.scoreText_label = SKLabelNode.init(text: "Score")
        self.score_label = SKLabelNode.init(text: String(scene.score))
        
        self.scoreText_label.fontSize = 64
        self.score_label.fontSize = 48
        self.scoreText_label.alpha = 0.5
        self.score_label.alpha = 0.5
        
        self.score_label.fontName = "Helvetica Neue Medium"
        self.scoreText_label.fontName = "Helvetica Neue Light"
        
        self.scoreText_label.position = Tools.fromSceneToWorldPosition(
            screenSpacePos: CGPoint(x: 0.5, y: 0.85))
        self.score_label.position = CGPoint(x: self.scoreText_label.position.x,
                                            y: self.scoreText_label.position.y - self.scoreText_label.frame.height - 10)
        self.save_button = Button(text: "Sauvegarder")
        self.save_button.position = Tools.fromSceneToWorldPosition(screenSpacePos: CGPoint(x: 0.5, y: 0.5))
        
        self.retry_button = Button(text: "Recommencer")
        self.retry_button.position = self.save_button.position
        self.retry_button.position.y -= self.save_button.size.height + 30
        
        self.menu_button = Button(text: "Accueil")
        self.menu_button.position = self.retry_button.position
        self.menu_button.position.y -= self.retry_button.size.height + 30
    }
    
    
    func hide() {
        self.scene.removeChildren(in: [self.scoreText_label,
                                       self.score_label,
                                       self.save_button,
                                       self.retry_button,
                                       self.menu_button])
    }
    
    func show() {
        self.score_label.text = String(self.scene.score)
        self.scene.addChild(self.scoreText_label)
        self.scene.addChild(self.score_label)
        self.scene.addChild(self.save_button)
        self.scene.addChild(self.retry_button)
        self.scene.addChild(self.menu_button)
    }
    
    
    func touchUp(_ pos: CGPoint) {
        if(save_button.isClicked(pos)) {  }
        else if(retry_button.isClicked(pos)) { hide(); scene.start() }
        else if(menu_button.isClicked(pos)) { hide(); self.scene.switchScreen(GameScene.GameState.welcome) }
    }
    
    
    
}
























