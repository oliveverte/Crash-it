//
//  Protocols.swift
//  Crash it
//
//  Created by Olivier Picard on 18/03/2018.
//  Copyright © 2018 Olivier Picard. All rights reserved.
//

import Foundation

protocol Collisionable {
    var enableCollision: Bool {get}
    var already_in_collision: Bool {get}
    
    func collisionEnter(item: MovingItem)
    func collisionLeave(item: MovingItem)
    func initCollider()
}
