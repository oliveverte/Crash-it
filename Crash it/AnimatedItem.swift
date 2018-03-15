//
//  AnimatedItem.swift
//  Crash it
//
//  Created by Olivier Picard on 13/03/2018.
//  Copyright © 2018 Olivier Picard. All rights reserved.
//

import Foundation
import SpriteKit

class AnimatedItem: MovingItem {
    var loop:Int
    var counterLoop:Int
    var textureGroup:[SKTexture]?
    
    init(textureGroup textures: [SKTexture], numberOfLooping loop: Int,
         spriteSize size: CGSize) {
        self.loop = loop
        self.counterLoop = 0
        self.textureGroup = textures
        super.init(texture: textures[0], color: UIColor.white, size: size)
    }
    
    
    required init?(coder aDecoder: NSCoder) {
        self.loop = 0
        self.counterLoop = 0
        self.textureGroup = nil
        super.init(coder: aDecoder)
    }
    
    
    func start() {
        
    }
    
    func stop() {
        
    }
    
}
