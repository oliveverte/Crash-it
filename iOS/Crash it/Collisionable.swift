//
//  Protocols.swift
//  Crash it
//
//  Created by Olivier Picard on 18/03/2018.
//  Copyright © 2018 Olivier Picard. All rights reserved.
//

import Foundation
import SpriteKit

protocol Collisionable {
    var enable_collision: Bool {get set}
    var size: CGSize {get set}
    var position: CGPoint {get set}
    
    func inCollisionWith(item: Collisionable)
    func isOverlaps(_ item: Collisionable) -> Bool
}
