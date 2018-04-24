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
    let scoreText_label: SKLabelNode
    var score_label: SKLabelNode
    let saved_label: SKLabelNode
    let savedScore_label: SKLabelNode
    var save_button:Button
    var retry_button:Button
    var menu_button:Button
    var saveScore_button:Button
    var timer:Timer!
    var enable_userInteraction: Bool
    var savedItems: [Tools.ItemConf]!
    
    
    
    init(scene: GameScene) {
        self.scene = scene
        self.enable_userInteraction = false
        
        self.score_label = SKLabelNode.init(text: String(scene.score))
        self.score_label.fontSize = 48
        self.score_label.alpha = 0.5
        self.score_label.fontName = "Helvetica Neue Medium"
        
        self.scoreText_label = SKLabelNode.init(text: "Score")
        self.scoreText_label.fontSize = 64
        self.scoreText_label.alpha = 0.5
        self.scoreText_label.fontName = "Helvetica Neue Light"
        
        self.saved_label = SKLabelNode.init(text: "La partie a été sauvegardée")
        self.saved_label.fontSize = 20
        self.saved_label.alpha = 0.5
        self.saved_label.fontName = "HelveticaNeue-Light"
        
        self.savedScore_label = SKLabelNode.init(text: "Le score a été sauvegardé")
        self.savedScore_label.fontSize = 20
        self.savedScore_label.alpha = 0.5
        self.savedScore_label.fontName = "HelveticaNeue-Light"
        
        self.scoreText_label.position = Tools.fromSceneToWorldPosition(
            screenSpacePos: CGPoint(x: 0.5, y: 0.85))
        
        self.score_label.position = CGPoint(x: self.scoreText_label.position.x,
                                            y: self.scoreText_label.position.y - self.scoreText_label.frame.height - 10)
        
        
        self.save_button = Button(text: "Sauvegarder la partie")
        self.save_button.position = Tools.fromSceneToWorldPosition(screenSpacePos: CGPoint(x: 0.5, y: 0.5))
        
        self.saveScore_button = Button(text: "Enregistrer le score")
        self.saveScore_button.position = self.save_button.position
        self.saveScore_button.position.y -= self.save_button.size.height + 30
        
        self.retry_button = Button(text: "Recommencer")
        self.retry_button.position = self.saveScore_button.position
        self.retry_button.position.y -= self.saveScore_button.size.height + 30
        
        self.menu_button = Button(text: "Accueil")
        self.menu_button.position = self.retry_button.position
        self.menu_button.position.y -= self.retry_button.size.height + 30
        
        self.saved_label.position = self.save_button.position
        self.savedScore_label.position = self.saveScore_button.position
    }
    
    
    func hide() {
        self.enable_userInteraction = false
        self.scene.removeChildren(in: [self.scoreText_label,
                                       self.score_label,
                                       self.save_button,
                                       self.retry_button,
                                       self.menu_button,
                                       self.saved_label,
                                       self.saveScore_button,
                                       self.savedScore_label])
    }
    
    func show() {
        self.score_label.text = String(self.scene.score)
        self.scene.addChild(self.scoreText_label)
        self.scene.addChild(self.score_label)
        self.scene.addChild(self.save_button)
        self.scene.addChild(self.retry_button)
        self.scene.addChild(self.menu_button)
        self.scene.addChild(self.saveScore_button)
        self.savedItems = self.scene.saveItems()
        self.timer = Timer.scheduledTimer(withTimeInterval: 0.7, repeats: false, block: { _ in
            self.enable_userInteraction = true
        })
    }
    
    
    func touchUp(_ pos: CGPoint) {
        if !self.enable_userInteraction { return }
        if(save_button.isClicked(pos)) {
            self.scene.removeChildren(in: [self.save_button])
            self.scene.addChild(self.saved_label)
            let encodedDatas = Tools.addEncodedSaveDatas(Tools.KEY_DEFAULT_GAMEINFOS, self.scene.score, self.savedItems)
            print(4)
            UserDefaults.standard.set(encodedDatas, forKey: Tools.KEY_DEFAULT_GAMEINFOS)
            print(5)
        }
        else if (saveScore_button.isClicked(pos)) {
            self.scene.removeChildren(in: [self.saveScore_button])
            self.scene.addChild(self.savedScore_label)
            let encodedDatas = Tools.addEncodedSaveDatas(Tools.KEY_DEFAULT_SCORES, self.scene.score, nil)
            UserDefaults.standard.set(encodedDatas, forKey: Tools.KEY_DEFAULT_SCORES)
        }
        else if(retry_button.isClicked(pos)) { hide(); scene.start() }
        else if(menu_button.isClicked(pos)) { hide(); self.scene.switchScreen(GameScene.GameState.welcome) }
    }
    
    
    
    
    
}
























