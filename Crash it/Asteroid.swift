//
//  Asteroid.swift
//  Crash it
//
//  Created by Olivier Picard on 13/03/2018.
//  Copyright © 2018 Olivier Picard. All rights reserved.
//

import Foundation
import SpriteKit

class Asteroid: AnimatedItem {
    init(size: CGSize) {
        let splitedAtlas:[SKTexture] = Tools.splitAtlas(atlas: SKTextureAtlas(named: "asteroids"),
                                                        baseName: "asteroid_")
        super.init(textureGroup: splitedAtlas,
                   numberOfLooping: nil,
                   size: size,
                   latency: 0.3)
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
    
}

