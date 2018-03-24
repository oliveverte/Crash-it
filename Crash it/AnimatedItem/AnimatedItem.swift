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
    var loop:Int?
    var counterLoop:Int
    var latency: TimeInterval
    var textures:[SKTexture]
    var enableAnimation: Bool
    private var previous_frame: TimeInterval
    private var index_current_texture: Int
    private var delete_atEnd: Bool
    
    
    init(textureGroup textures: [SKTexture], numberOfLooping loop: Int?,
         size: CGSize, latency: TimeInterval, deleteAtEnd: Bool = true) {
        self.loop = loop
        self.counterLoop = 0
        self.textures = textures
        self.latency = latency
        self.previous_frame = 0
        self.index_current_texture = 0
        self.enableAnimation = true
        self.delete_atEnd = deleteAtEnd
        super.init(texture: textures[0], color: UIColor.white, size: size)
    }
    
    
    required init?(coder aDecoder: NSCoder) {
        self.textures = []
        self.counterLoop = 0
        self.latency = 0
        self.previous_frame = 0
        self.index_current_texture = 0
        self.enableAnimation = false
        self.delete_atEnd = true
        super.init(coder: aDecoder)
    }
    
    
    override func update(_ currentTime: TimeInterval) {
        super.update(currentTime)
        if(!enableAnimation) { return }
        if (self.loop ?? 0) > 0 && self.counterLoop >= (self.loop ?? 0) {
            if(self.delete_atEnd) {self.scene?.removeChildren(in: [self])}
            return
        }
        if currentTime - self.previous_frame < self.latency { return }
        
        if self.index_current_texture + 1 >= self.textures.count {
            self.index_current_texture = 0
            self.counterLoop += 1;
        } else { self.index_current_texture += 1 }
        
        self.texture = self.textures[self.index_current_texture]
        self.previous_frame = currentTime
        
    }
    
}
