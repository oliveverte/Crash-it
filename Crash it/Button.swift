//
//  Button.swift
//  Crash it
//
//  Created by Olivier Picard on 25/03/2018.
//  Copyright © 2018 Olivier Picard. All rights reserved.
//

import Foundation
import SpriteKit

class Button: SKSpriteNode {
    private let label: SKLabelNode
    var text:String {
        get { return self.label.text! }
        set{ self.label.text = newValue }
    }
    
    init(text: String, size: CGSize) {
        self.label = SKLabelNode(text: text)
        super.init(texture: nil,
                   color: UIColor(white: 1, alpha: 0.6),
                   size: size)
        self.anchorPoint = CGPoint(x: 0.5, y: 0.5)
        self.label.fontSize = 20
        self.label.position = CGPoint(x: self.position.x, y: self.position.y - self.label.frame.height/3)
        self.label.fontName = "Helvetica Neue Light"
        self.label.fontColor = UIColor.black
        self.addChild(self.label)
        self.zPosition = 1000
    }
    
    convenience init(text: String) {
        self.init(text: text, size: CGSize(width: 200, height: 40))
    }
    
    required init?(coder aDecoder: NSCoder) {
        self.label = SKLabelNode(text: "")
        super.init(coder: aDecoder)
    }
    
    func isClicked(_ touchAt: CGPoint) -> Bool {
        if(touchAt.x > self.position.x - self.size.width/2
            && touchAt.x < self.position.x + self.size.width/2
            && touchAt.y < self.position.y + self.size.height/2
            && touchAt.y > self.position.y - self.size.height/2) {
            return true
        }
        return false
    }
    
}
