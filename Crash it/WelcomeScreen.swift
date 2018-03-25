//
//  WelcomeScreen.swift
//  Crash it
//
//  Created by Olivier Picard on 25/03/2018.
//  Copyright © 2018 Olivier Picard. All rights reserved.
//

import Foundation
import SpriteKit

class WelcomeScreen {
    let scene: GameScene
    let title_label: SKLabelNode
    let play_button: Button
    let resume_button: Button
    let score_button: Button
    
    init(scene: GameScene) {
        self.scene = scene
        
        self.title_label = SKLabelNode.init(text: "Crash it")
        self.title_label.alpha = 0.5
        self.title_label.fontName = "HelveticaNeue-Light"
        self.title_label.fontSize = 55
        self.title_label.fontColor = UIColor.white
        
        self.play_button = Button(text: "Jouer")
        self.resume_button = Button(text: "Reprendre")
        self.score_button = Button(text: "Scores")
        
        self.title_label.position = Tools.fromSceneToWorldPosition(screenSpacePos: CGPoint(x: 0.5, y: 0.8))
        self.play_button.position = Tools.fromSceneToWorldPosition(screenSpacePos: CGPoint(x: 0.5, y: 0.5))
        self.resume_button.position = CGPoint(x: self.play_button.position.x,
                                              y: self.play_button.position.y - self.play_button.size.height - 30)
        self.score_button.position = CGPoint(x: self.resume_button.position.x,
                                             y: self.resume_button.position.y - self.resume_button.size.height - 30)
        
        
    }
    
    func show() {
        self.scene.addChild(self.title_label)
        self.scene.addChild(self.play_button)
        self.scene.addChild(self.resume_button)
        self.scene.addChild(self.score_button)
    }
    
    func hide() {
        self.scene.removeChildren(in: [self.title_label,
                                       self.play_button,
                                       self.resume_button,
                                       self.score_button])
    }
    
    func touchUp(_ pos: CGPoint) {
        if self.play_button.isClicked(pos) { print("play"); hide(); self.scene.start() }
        else if self.resume_button.isClicked(pos) { }
        else if self.score_button.isClicked(pos) { }
    }
}



























