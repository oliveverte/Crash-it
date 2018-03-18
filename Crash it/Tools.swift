//
//  Tools.swift
//  Crash it
//
//  Created by Olivier Picard on 18/03/2018.
//  Copyright © 2018 Olivier Picard. All rights reserved.
//

import Foundation
import SpriteKit

class Tools {
    static var scene_size: CGSize?
    
    
    /**
     Convert a percentage of the current scene to a real point on screen (physic pixel coordinate).
     
     - returns:
     CGPoint represent coordinate of a point on screen (pixel).
     
     - parameters:
     - pos: CGpoint: between [0,1] on the scene (percentage of the scene).
     */
    static func fromSceneToWorldPosition(screenSpacePos pos: CGPoint) -> CGPoint {
        return CGPoint(x: (Tools.scene_size?.width)! * pos.x,
                       y: (Tools.scene_size?.height)! * pos.y)
    }
    
    
    static func fromSceneToWorldSize(sceneSpaceSize size: CGSize) -> CGSize {
        return CGSize(width: (Tools.scene_size?.width)! * size.width,
                      height: (Tools.scene_size?.height)! * size.height)
    }
}
