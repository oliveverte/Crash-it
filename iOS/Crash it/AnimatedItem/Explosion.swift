//
//  Explosion.swift
//  Crash it
//
//  Created by Olivier Picard on 24/03/2018.
//  Copyright © 2018 Olivier Picard. All rights reserved.
//

import Foundation
import SpriteKit

class Explosion: AnimatedItem {
    
    init(position: CGPoint, size: CGSize) {
        let splitedAtlas:[SKTexture] = Tools.splitAtlas(atlas: SKTextureAtlas(named: "Explosions"),
                                                        baseName: "explosion_")
        super.init(textureGroup: splitedAtlas,
                   numberOfLooping: 1,
                   size: size,
                   latency: 0.1)
        self.position = position
        self.zPosition = 500
    }
    
    convenience init(position: CGPoint) {
        self.init(position: position,
                  size: CGSize(width: 70, height: 70))
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
    
}
