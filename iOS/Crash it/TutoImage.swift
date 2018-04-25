//
//  TutoImage.swift
//  Crash it
//
//  Created by Olivier Picard on 25/04/2018.
//  Copyright © 2018 Olivier Picard. All rights reserved.
//

import Foundation
import SpriteKit

class TutoImage {
    var click_left: SKSpriteNode
    var click_right: SKSpriteNode
    var middle_line: SKSpriteNode
    var explain_text: SKLabelNode
    let scene: SKScene
    
    init(_ scene: SKScene) {
        
        self.scene = scene
        self.middle_line = SKSpriteNode(color: UIColor.white, size: CGSize(width: 2, height: scene.size.height))
        self.middle_line.position = Tools.fromSceneToWorldPosition(screenSpacePos: CGPoint(x: 0.5, y: 0.5))
        
        self.click_left = SKSpriteNode(texture: SKTexture(image: #imageLiteral(resourceName: "pointer")), color: UIColor.white, size: CGSize(width: 60, height: 60))
        self.click_left.position = Tools.fromSceneToWorldPosition(screenSpacePos: CGPoint(x: 0.25, y: 0.3))
        
        self.click_right = SKSpriteNode(texture: SKTexture(image: #imageLiteral(resourceName: "pointer")), color: UIColor.white, size: CGSize(width: 70, height: 70))
        self.click_right.position = Tools.fromSceneToWorldPosition(screenSpacePos: CGPoint(x: 0.75, y: 0.4))
        
        self.explain_text = SKLabelNode(text: "Cliquez à droite ou à gauche de l'écran\nPour déplacer le vaisseau")
        self.explain_text.numberOfLines = 0
        self.explain_text.horizontalAlignmentMode = .center
        self.explain_text.preferredMaxLayoutWidth = scene.size.width - 40
        self.explain_text.alpha = 0.7
        self.explain_text.fontName = "HelveticaNeue-Light"
        self.explain_text.fontSize = 15
        self.explain_text.fontColor = UIColor.white
        self.explain_text.position = Tools.fromSceneToWorldPosition(screenSpacePos: CGPoint(x: 0.5, y: 0.6))
        
        click_left.alpha = 0.5
        click_right.alpha = 0.5
        middle_line.alpha = 0.35
        
        self.scene.addChild(self.click_left)
        self.scene.addChild(self.click_right)
        self.scene.addChild(self.middle_line)
        self.scene.addChild(self.explain_text)
    }
    
    deinit {
        self.scene.removeChildren(in: [self.click_left, self.click_right, self.middle_line, self.explain_text])
    }
}
