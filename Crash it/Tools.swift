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
    /**
     Convert a percentage of the current scene to a real point on screen (physic pixel coordinate).
     
     - returns:
     CGPoint represent coordinate of a point on screen (pixel).
     
     - parameters:
     - pos: CGpoint: between [0,1] on the scene (percentage of the scene).
     */
    static func fromSceneToWorldPosition(screenSpacePos pos: CGPoint, sceneSize: CGSize) -> CGPoint {
        return CGPoint(x: sceneSize.width * pos.x,
                       y: sceneSize.height * pos.y)
    }
    
    
    static func fromSceneToWorldSize(sceneSpaceSize size: CGSize, sceneSize: CGSize) -> CGSize {
        return CGSize(width: sceneSize.width * size.width,
                      height: sceneSize.height * size.height)
    }
}
